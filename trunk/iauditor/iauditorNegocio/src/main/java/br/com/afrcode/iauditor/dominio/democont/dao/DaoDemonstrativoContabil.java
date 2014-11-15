package br.com.afrcode.iauditor.dominio.democont.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;
import br.com.afrcode.iauditor.dominio.elasticsearch.ExtendedStats;
import br.com.afrcode.iauditor.spring.config.elasticsearch.ElasticsearchPropertiesConfig.ElasticSearchProperties;
import br.com.afrcode.iauditor.util.elasticsearch.ElasticsearchUrl;

import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.Json;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Repository
public class DaoDemonstrativoContabil {
	private static final Logger LOG = Logger
			.getLogger(DaoDemonstrativoContabil.class);
	@Autowired
	private ElasticSearchProperties elasticSearchProperties;

	@Autowired
	private HttpRequestFactory requestFactory;

	public String recuperarContas(String entidade, LocalDate dtMin,
			LocalDate dtMax) {
		String index = elasticSearchProperties.getElasticsearchDemoContIndex();
		String type = elasticSearchProperties
				.getElasticsearchDemoConsolResultType();
		String query = "_search?pretty=true";
		ElasticsearchUrl url = ElasticsearchUrl.get(index, type, query);

		final String queryObject = "{ \"query\": { \"filtered\": { \"query\": "
				+ "{ \"match\": { \"ENTIDADE\": \"" + entidade + "\" } }, "
				+ "\"filter\": { \"range\": { " + "\"PERÍODO\": { \"from\": \""
				+ dtMin.toString() + "\"," + " \"to\": \"" + dtMax.toString()
				+ "\" } } } } } }";
		try {
			HttpContent content = new InputStreamContent(Json.MEDIA_TYPE,
					new ByteArrayInputStream(queryObject.getBytes()));
			HttpRequest request = requestFactory.buildPostRequest(url, content);
			HttpResponse response = request.execute();
			String r = response.parseAsString();
			if (LOG.isDebugEnabled()) {
				LOG.debug(r);
			}
			return r;
		} catch (IOException e) {
			throw new ExcecaoNaoPrevista(e);
		}
	}

	public ExtendedStats getExtendedStats(String field) {
		String index = elasticSearchProperties.getElasticsearchDemoContIndex();
		String type = elasticSearchProperties
				.getElasticsearchDemoConsolResultType();
		String query = "_search?pretty=true";
		ElasticsearchUrl url = ElasticsearchUrl.get(index, type, query);

		final String queryObject = "{ \"aggs\": { \"" + field
				+ "\": { \"extended_stats\": { \"field\": \"" + field
				+ "\" } } } }";
		try {
			HttpContent content = new InputStreamContent(Json.MEDIA_TYPE,
					new ByteArrayInputStream(queryObject.getBytes()));
			HttpRequest request = requestFactory.buildPostRequest(url, content);
			HttpResponse response = request.execute();
			String r = response.parseAsString();
			if (LOG.isDebugEnabled()) {
				LOG.debug(r);
			}
			return parseAsExtendedStats(r, field);
		} catch (IOException e) {
			throw new ExcecaoNaoPrevista(e);
		}
	}

	private ExtendedStats parseAsExtendedStats(String r, String statsRMemberName) {
		JsonParser jsonParser = new JsonParser();
		JsonObject rAsJsonObject = jsonParser.parse(r).getAsJsonObject();
		final String aggsRMemberName = "aggregations";
		JsonObject aggsMember = rAsJsonObject.get(aggsRMemberName)
				.getAsJsonObject();
		JsonElement statsMember = aggsMember.get(statsRMemberName);
		return ExtendedStats.fromJson(statsMember);
	}
}
