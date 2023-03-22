package com.meawallet.weatherapp.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Builder
public record WeatherDataOutDto(
        @JsonProperty(value = "time")
        ZonedDateTime time,
        @JsonProperty(value = "air_temperature")
        BigDecimal airTemperature
) {
}
