package com.meawallet.weatherapp.core.port.out;

import com.meawallet.weatherapp.domain.WeatherData;

public interface UpdateWeatherDataPort {

    void update(WeatherData weatherData);
}
