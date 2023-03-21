package com.meawallet.weatherapp.out.converter;

import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.out.dto.HourlyWeatherData;
import org.springframework.stereotype.Component;

@Component
public class HourlyWeatherDataDtoToWeatherDataDomainConverter {

    public WeatherData convert(HourlyWeatherData hourlyWeatherDataDto) {
        return WeatherData.builder()
                .timestamp(hourlyWeatherDataDto.time())
                .temperature(hourlyWeatherDataDto.airTemperature())
                .build();
    }
}
