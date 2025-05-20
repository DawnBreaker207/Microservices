package com.dawn.microservice.inventory.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.dawn.microservice.inventory.client.ProductClient;

import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {
    @Value("${product.service.url}")
    private String productServiceUrl;
    private final ObservationRegistry observationRegistry;

    @Bean
    public ProductClient productClient() {
	RestClient restClient = RestClient.builder().baseUrl(productServiceUrl)
		.requestFactory(getClientRequestFactory()).observationRegistry(observationRegistry).build();

	var restClientAdapter = RestClientAdapter.create(restClient);
	var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
	return httpServiceProxyFactory.createClient(ProductClient.class);
    }

    private ClientHttpRequestFactory getClientRequestFactory() {
	ClientHttpRequestFactorySettings clientHttpRequestFactorySettings = ClientHttpRequestFactorySettings.DEFAULTS
		.withConnectTimeout(Duration.ofSeconds(3)).withReadTimeout(Duration.ofSeconds(3));

	return ClientHttpRequestFactories.get(clientHttpRequestFactorySettings);
    }

}
