package com.meawallet.weatherapp.out.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WeatherApiConfig {
    @NotBlank
    private String weatherUrl;
}
