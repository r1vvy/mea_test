package com.meawallet.weatherapp.out.dto;

import java.util.List;

public record GetWeatherDataOutResponse(
        List<HourlyWeatherData> timeseriesData
) {
}
