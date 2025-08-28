package com.spring.cloud.circuit.breaker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ExecutorService executorService(){
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean
    public RestClient restClient(@Value("${review.service.endpoint.url}") String endPointUrl) {
        return RestClient.builder()
                .baseUrl(endPointUrl)
                .build();
    }
}
