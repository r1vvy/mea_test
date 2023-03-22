package com.meawallet.weatherapp.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record Location(
        Integer id,
        BigDecimal latitude,
        BigDecimal longitude,
        WeatherData weatherData
) {
}
