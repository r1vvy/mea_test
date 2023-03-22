package com.meawallet.weatherapp.repository.adapter;

import com.meawallet.weatherapp.core.port.out.SaveWeatherDataPort;
import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.repository.converter.WeatherDataDomainToEntityConverter;
import com.meawallet.weatherapp.repository.converter.WeatherDataEntityToDomainConverter;
import com.meawallet.weatherapp.repository.repository.WeatherDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveWeatherDataAdapter implements SaveWeatherDataPort {
    private final WeatherDataRepository weatherDataRepository;
    private final WeatherDataDomainToEntityConverter weatherDataDomainToEntityConverter;
    private final WeatherDataEntityToDomainConverter weatherDataEntityToDomainConverter;

    @Override
    public WeatherData save(WeatherData weatherData) {
        var weatherDataEntity = weatherDataDomainToEntityConverter.convert(weatherData);
        var savedEntity = weatherDataRepository.save(weatherDataEntity);

        return weatherDataEntityToDomainConverter.convert(savedEntity);
    }
}
