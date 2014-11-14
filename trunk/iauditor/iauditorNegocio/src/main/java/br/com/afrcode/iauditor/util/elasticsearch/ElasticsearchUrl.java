package br.com.afrcode.iauditor.util.elasticsearch;

import org.apache.commons.lang.Validate;

import br.com.afrcode.arquitetura.util.contexto.ApplicationContextUtils;
import br.com.afrcode.iauditor.spring.config.elasticsearch.ElasticsearchPropertiesConfig.ElasticSearchProperties;

import com.google.api.client.http.GenericUrl;

public class ElasticsearchUrl extends GenericUrl {

	private ElasticsearchUrl(String encodedUrl) {
		super(encodedUrl);
	}

	public static ElasticsearchUrl get(String index, String type, String query) {
		Validate.notNull(index);
		ElasticSearchProperties elasticsearchProps = ApplicationContextUtils
				.getBean(ElasticSearchProperties.class);
		final String prefix = "/";
		String _index = index.startsWith(prefix) ? index : prefix.concat(index);
		String _type = "";
		if (type != null) {
			_type = type.startsWith(prefix) ? type : prefix.concat(type);
		}
		String _query = "";
		if (query != null) {
			_query = query.startsWith(prefix) ? query : prefix.concat(query);
		}
		return new ElasticsearchUrl(
				elasticsearchProps.getElasticsearchServerURI() + _index + _type
						+ _query);
	}

}
