package br.com.afrcode.iauditor.spring.config.httpclient;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.gson.GsonFactory;

@Configuration
public class HttpRequestFactoryConfig {

	@Bean
	public HttpRequestFactory httpRequestFactory() {
		final JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
		final HttpTransport httpTransport = new NetHttpTransport();
		HttpRequestFactory requestFactory = httpTransport
				.createRequestFactory(new HttpRequestInitializer() {
					@Override
					public void initialize(HttpRequest request)
							throws IOException {
						request.setParser(new JsonObjectParser(jsonFactory));
					}
				});
		return requestFactory;
	}

}
