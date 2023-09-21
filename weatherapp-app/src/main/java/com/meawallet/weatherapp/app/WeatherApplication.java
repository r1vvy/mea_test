package com.meawallet.weatherapp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.meawallet.weatherapp")
public class WeatherApplication {
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(WeatherApplication.class);
        app.setAdditionalProfiles("dev");
        app.run(args);
    }
}
