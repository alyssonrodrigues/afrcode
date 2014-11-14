package br.com.afrcode.iauditor.dominio.democont.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.afrcode.arquitetura.util.excecao.ExcecaoNaoPrevista;
import br.com.afrcode.iauditor.spring.config.elasticsearch.ElasticsearchPropertiesConfig.ElasticSearchProperties;
import br.com.afrcode.iauditor.util.elasticsearch.ElasticsearchUrl;

import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.Json;

@Repository
public class DaoDemonstrativoContabil {
	private static final Logger LOG = Logger.getLogger(DaoDemonstrativoContabil.class);
	@Autowired
	private ElasticSearchProperties elasticSearchProperties;

	@Autowired
	private HttpRequestFactory requestFactory;

	public String getExtendedStats(String label) {
		String index = elasticSearchProperties.getElasticsearchDemoContIndex();
		String type = elasticSearchProperties
				.getElasticsearchDemoConsolResultType();
		String query = "_search?pretty=true";
		ElasticsearchUrl url = ElasticsearchUrl.get(index, type, query);

		final String queryObject = "{ \"aggs\": { \"EXT STATS " + label
				+ "\": { \"extended_stats\": { \"field\": \"" + label
				+ "\" } } } }";
		try {
			HttpContent content = new InputStreamContent(Json.MEDIA_TYPE,
					new ByteArrayInputStream(queryObject.getBytes()));
			HttpRequest request = requestFactory.buildPostRequest(url, content);
			HttpResponse response = request.execute();
			String r = response.parseAsString();
			LOG.info(r);
			return r;
		} catch (IOException e) {
			throw new ExcecaoNaoPrevista(e);
		}
	}
}
