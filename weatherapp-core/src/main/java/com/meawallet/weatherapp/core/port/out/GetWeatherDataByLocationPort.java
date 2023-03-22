package com.meawallet.weatherapp.core.port.out;

import com.meawallet.weatherapp.domain.WeatherData;

import java.math.BigDecimal;
import java.util.Optional;

public interface GetWeatherDataByLocationPort {
    Optional<WeatherData> getWeatherDataByLocationLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);
}
