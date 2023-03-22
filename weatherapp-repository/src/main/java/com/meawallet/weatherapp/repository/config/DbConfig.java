package com.meawallet.weatherapp.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.meawallet.weatherapp")
@EntityScan(basePackages = "com.meawallet.weatherapp")
public class DbConfig {
}
