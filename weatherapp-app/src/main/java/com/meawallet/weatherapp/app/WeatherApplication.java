package com.meawallet.weatherapp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = "com.meawallet.weatherapp")
// IF CANT LOAD .ENV FILE, TRY THIS:
//@PropertySource(value = "file:./.env")
public class WeatherApplication {
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(WeatherApplication.class);
        app.setAdditionalProfiles("dev");
        app.run(args);
    }
}
