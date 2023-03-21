package com.meawallet.weatherapp.core.port.in;

import com.meawallet.weatherapp.domain.WeatherData;

import java.math.BigDecimal;

public interface GetWeatherDataByLocationLatitudeAndLongitudeUseCase {
    WeatherData getWeatherData(BigDecimal latitude, BigDecimal longitude);
}
