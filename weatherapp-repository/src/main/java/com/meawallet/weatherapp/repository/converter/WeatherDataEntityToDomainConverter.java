package com.meawallet.weatherapp.repository.converter;

import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.repository.entity.WeatherDataEntity;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataEntityToDomainConverter {

    public WeatherData convert(WeatherDataEntity entity) {
        return WeatherData.builder()
                .id(entity.getId())
                .airTemperature(entity.getAirTemperature())
                .timestamp(entity.getTimestamp())
                .build();
    }
}
