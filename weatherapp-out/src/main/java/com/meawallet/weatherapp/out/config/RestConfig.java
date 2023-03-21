package com.meawallet.weatherapp.out.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Bean
    public RestTemplate restTemplate() {
        var restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new UserAgentInterceptor());

        return restTemplate;
    }

    @Bean
    @ConfigurationProperties(prefix = "weather-api")
    public WeatherApiConfig weatherApiConfig() {
        return new WeatherApiConfig();
    }
}
