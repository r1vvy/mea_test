package com.meawallet.weatherapp.repository.adapter;

import com.meawallet.weatherapp.core.exception.NoDataFoundException;
import com.meawallet.weatherapp.core.port.out.UpdateWeatherDataPort;
import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.repository.converter.WeatherDataDomainToEntityConverter;
import com.meawallet.weatherapp.repository.repository.WeatherDataRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UpdateWeatherDataAdapter implements UpdateWeatherDataPort {
    private final WeatherDataRepository weatherDataRepository;
    private final WeatherDataDomainToEntityConverter weatherDataDomainToEntityConverter;
    @Override
    public void update(WeatherData weatherData) {
        var entity =  weatherDataRepository.findById(weatherData.id())
                .orElseThrow(() -> new NoDataFoundException("No WeatherData Entity found with id=" + weatherData.id()));

        weatherDataRepository.save(entity);

        log.debug("WeatherData entity updated successfully: {}", entity);
    }
}
