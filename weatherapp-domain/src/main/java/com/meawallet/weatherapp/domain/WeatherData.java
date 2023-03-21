package com.meawallet.weatherapp.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Builder(toBuilder = true)
public record WeatherData(
        Integer id,
        Location location,
        BigDecimal temperature,
        ZonedDateTime timestamp
) {
}
