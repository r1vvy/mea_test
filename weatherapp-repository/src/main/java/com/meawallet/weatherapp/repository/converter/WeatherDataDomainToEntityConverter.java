package com.meawallet.weatherapp.repository.converter;

import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.repository.entity.WeatherDataEntity;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataDomainToEntityConverter {

    public WeatherDataEntity convert(WeatherData weatherData) {
        return WeatherDataEntity.builder()
                .id(weatherData.id())
                .airTemperature(weatherData.airTemperature())
                .timestamp(weatherData.timestamp())
                .build();
    }
}
