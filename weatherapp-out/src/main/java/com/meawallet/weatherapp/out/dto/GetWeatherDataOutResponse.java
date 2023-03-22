package com.meawallet.weatherapp.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = WeatherDataOutDeserializer.class)
public record GetWeatherDataOutResponse(
        @JsonProperty("timeseries")
        List<WeatherDataOutDto> timeseriesData
) {
}
