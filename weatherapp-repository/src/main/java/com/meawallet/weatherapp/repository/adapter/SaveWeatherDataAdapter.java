package com.meawallet.weatherapp.repository.adapter;

import com.meawallet.weatherapp.core.port.out.SaveWeatherDataPort;
import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.repository.converter.WeatherDataDomainToEntityConverter;
import com.meawallet.weatherapp.repository.converter.WeatherDataEntityToDomainConverter;
import com.meawallet.weatherapp.repository.repository.WeatherDataRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
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

        log.debug("WeatherData entity saved successfully: {}", savedEntity);

        return weatherDataEntityToDomainConverter.convert(savedEntity);
    }
}
