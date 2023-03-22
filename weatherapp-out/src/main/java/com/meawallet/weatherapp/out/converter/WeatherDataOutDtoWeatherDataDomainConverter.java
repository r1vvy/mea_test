package com.meawallet.weatherapp.out.converter;

import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.out.dto.GetWeatherDataOutResponse;
import com.meawallet.weatherapp.out.dto.WeatherDataOutDto;
import org.springframework.stereotype.Component;

@Component
public class WeatherDataOutDtoWeatherDataDomainConverter {

    public WeatherData convert(WeatherDataOutDto dto) {
        return WeatherData.builder()
                .timestamp(dto.time())
                .airTemperature(dto.airTemperature())
                .build();
    }
}
