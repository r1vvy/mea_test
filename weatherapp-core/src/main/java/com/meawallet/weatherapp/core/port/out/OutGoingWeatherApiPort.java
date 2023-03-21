package com.meawallet.weatherapp.core.port.out;

import com.meawallet.weatherapp.domain.WeatherData;

import java.math.BigDecimal;
import java.util.List;

public interface OutGoingWeatherApiPort {
    List<WeatherData> getWeatherData(BigDecimal latitude, BigDecimal longitude);
}
