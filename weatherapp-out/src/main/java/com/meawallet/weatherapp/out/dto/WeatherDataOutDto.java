package com.meawallet.weatherapp.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record WeatherDataOutDto(
        @JsonProperty(value = "time")
        ZonedDateTime time,
        @JsonProperty(value = "air_temperature")
        BigDecimal airTemperature
) {
}
