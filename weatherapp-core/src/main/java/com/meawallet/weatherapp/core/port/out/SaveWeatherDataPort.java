package com.meawallet.weatherapp.core.port.out;

import com.meawallet.weatherapp.domain.WeatherData;

public interface SaveWeatherDataPort {

    WeatherData save(WeatherData weatherData);
}
