package com.meawallet.weatherapp.out.config;

import jakarta.validation.constraints.NotBlank;

public class WeatherApiConfig {
    @NotBlank
    private String weatherUrl;
}
