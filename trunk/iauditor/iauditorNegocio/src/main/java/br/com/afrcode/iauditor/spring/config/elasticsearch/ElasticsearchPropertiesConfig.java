package br.com.afrcode.iauditor.spring.config.elasticsearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({ "classpath:elasticsearch.properties" })
public class ElasticsearchPropertiesConfig {

	@Value("${elasticsearch.server.uri}")
	private String elasticsearchServerURI;

	@Value("${elasticsearch.democont.index}")
	private String elasticsearchDemoContIndex;

	@Value("${elasticsearch.democonsolresult.type}")
	private String elasticsearchDemoConsolResultType;

	@Bean
	public ElasticSearchProperties elasticSearchProperties() {
		return new ElasticSearchProperties(elasticsearchServerURI,
				elasticsearchDemoContIndex, elasticsearchDemoConsolResultType);
	}

	public static class ElasticSearchProperties {
		private final String elasticsearchServerURI;
		private final String elasticsearchDemoContIndex;
		private final String elasticsearchDemoConsolResultType;

		public ElasticSearchProperties(String elasticsearchServerURI,
				String elasticsearchDemocontIndex,
				String elasticsearchDemoconsolresultType) {
			super();
			this.elasticsearchServerURI = elasticsearchServerURI;
			this.elasticsearchDemoContIndex = elasticsearchDemocontIndex;
			this.elasticsearchDemoConsolResultType = elasticsearchDemoconsolresultType;
		}

		public String getElasticsearchDemoConsolResultType() {
			return elasticsearchDemoConsolResultType;
		}

		public String getElasticsearchDemoContIndex() {
			return elasticsearchDemoContIndex;
		}

		public String getElasticsearchServerURI() {
			return elasticsearchServerURI;
		}

	}

}
