package br.com.afrcode.iauditor.dominio.democont.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;
import br.com.afrcode.iauditor.dominio.democont.DemonstrativoConsolidado;
import br.com.afrcode.iauditor.dominio.elasticsearch.ExtendedStats;
import br.com.afrcode.iauditor.spring.config.elasticsearch.ElasticsearchPropertiesConfig.ElasticSearchProperties;
import br.com.afrcode.iauditor.util.elasticsearch.ElasticsearchUrl;

import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.Json;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Repository
public class DaoDemonstrativoConsolidado {
	private static final Logger LOG = Logger
			.getLogger(DaoDemonstrativoConsolidado.class);

	private static final String PERIODO_PATTERN = DemonstrativoConsolidado.PERIODO_PATTERN;

	@Autowired
	private ElasticSearchProperties elasticSearchProperties;

	@Autowired
	private HttpRequestFactory requestFactory;

	public ExtendedStats getExtendedStats(String entidade, LocalDate dtMin,
			LocalDate dtMax, String labelConta, String field) {
		String index = elasticSearchProperties.getElasticsearchDemoContIndex();
		String type = elasticSearchProperties
				.getElasticsearchDemoConsolResultType();
		String query = "_search?pretty=true";
		ElasticsearchUrl url = ElasticsearchUrl.get(index, type, query);

		final String queryObject = "{ \"aggs\": { \"democonsolresult\": "
				+ "{ \"filter\": { \"query\": { \"filtered\": "
				+ "{ \"query\": { \"match\": " + "{ \"entidade\": \""
				+ entidade + "\" } }, "
				+ "\"filter\": { \"range\": { \"periodo\": { \"from\": \""
				+ dtMin.toString(PERIODO_PATTERN) + "\", \"to\": \""
				+ dtMax.toString(PERIODO_PATTERN)
				+ "\" } } } } } }, \"aggs\": { \"contas\": "
				+ "{ \"nested\": { \"path\": \"contas\" }, \"aggs\": { \""
				+ labelConta + "\": { \"filter\": { \"query\": "
				+ "{ \"match\": { \"contas.label\": { \"query\": \""
				+ labelConta + "\", \"type\": \"phrase\" } } } }, "
				+ "\"aggs\": { \"ext_stats\": "
				+ "{ \"extended_stats\": { \"field\": \"contas." + field
				+ "\" } } } } } } } } } } }";
		try {
			HttpContent content = new InputStreamContent(Json.MEDIA_TYPE,
					new ByteArrayInputStream(queryObject.getBytes()));
			HttpRequest request = requestFactory.buildPostRequest(url, content);
			HttpResponse response = request.execute();
			String r = response.parseAsString();
			if (LOG.isDebugEnabled()) {
				LOG.debug("getExtendedStats:" + r);
			}
			return parseAsExtendedStats(r, labelConta);
		} catch (IOException e) {
			throw new ExcecaoNaoPrevista(e);
		}
	}

	private List<DemonstrativoConsolidado> parseAsDemonstrativosConsolidados(
			String r) {
		List<DemonstrativoConsolidado> demosConsolidados = new ArrayList<DemonstrativoConsolidado>();
		JsonParser jsonParser = new JsonParser();
		JsonObject rAsJsonObject = jsonParser.parse(r).getAsJsonObject();
		final String hitsMemberName = "hits";
		final String sourceMemberName = "_source";
		JsonArray hits = rAsJsonObject.get(hitsMemberName).getAsJsonObject()
				.get(hitsMemberName).getAsJsonArray();
		for (Iterator<JsonElement> it = hits.iterator(); it.hasNext();) {
			JsonElement sourceMember = it.next().getAsJsonObject()
					.get(sourceMemberName);
			demosConsolidados.add(DemonstrativoConsolidado
					.fromJson(sourceMember));
		}
		return demosConsolidados;
	}

	private ExtendedStats parseAsExtendedStats(String r, String labelContaBucket) {
		JsonParser jsonParser = new JsonParser();
		JsonObject rAsJsonObject = jsonParser.parse(r).getAsJsonObject();
		final String aggsBucket = "aggregations";
		JsonObject aggsMember = rAsJsonObject.get(aggsBucket).getAsJsonObject();
		final String demoConsolResultBucket = "democonsolresult";
		final String contasBucket = "contas";
		final String extStatsBucket = "ext_stats";
		JsonElement statsMember = aggsMember.get(demoConsolResultBucket)
				.getAsJsonObject().get(contasBucket).getAsJsonObject()
				.get(labelContaBucket).getAsJsonObject().get(extStatsBucket);
		return ExtendedStats.fromJson(statsMember);
	}

	public List<DemonstrativoConsolidado> recuperarDemonstrativosConsolidados(
			String entidade, LocalDate dtMin, LocalDate dtMax) {
		String index = elasticSearchProperties.getElasticsearchDemoContIndex();
		String type = elasticSearchProperties
				.getElasticsearchDemoConsolResultType();
		String query = "_search?pretty=true";
		ElasticsearchUrl url = ElasticsearchUrl.get(index, type, query);

		final String queryObject = "{ \"query\": { \"filtered\": { \"query\": "
				+ "{ \"match\": { \"entidade\": \"" + entidade + "\" } }, "
				+ "\"filter\": { \"range\": { " + "\"periodo\": { \"from\": \""
				+ dtMin.toString(PERIODO_PATTERN) + "\"," + " \"to\": \""
				+ dtMax.toString(PERIODO_PATTERN) + "\" } } } } } }";
		try {
			HttpContent content = new InputStreamContent(Json.MEDIA_TYPE,
					new ByteArrayInputStream(queryObject.getBytes()));
			HttpRequest request = requestFactory.buildPostRequest(url, content);
			HttpResponse response = request.execute();
			String r = response.parseAsString();
			if (LOG.isDebugEnabled()) {
				LOG.debug("recuperarDemonstrativosConsolidados:" + r);
			}
			return parseAsDemonstrativosConsolidados(r);
		} catch (IOException e) {
			throw new ExcecaoNaoPrevista(e);
		}
	}
}
