package com.meawallet.weatherapp.repository.adapter;

import com.meawallet.weatherapp.core.port.out.GetWeatherDataByLocationPort;
import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.repository.converter.WeatherDataEntityToDomainConverter;
import com.meawallet.weatherapp.repository.repository.LocationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class GetWeatherDataByLocationLatitudeAndLongitudeAdapter implements GetWeatherDataByLocationPort {

    private final LocationRepository locationRepository;
    private final WeatherDataEntityToDomainConverter weatherDataEntityToDomainConverter;

    @Override
    public Optional<WeatherData> getWeatherDataByLocationLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude) {
        var locationEntity = locationRepository.findByLatitudeAndLongitude(latitude, longitude);
        log.debug("Location found by latitude and longitude: {}, {}", latitude, longitude);

        return locationEntity.map(entity -> weatherDataEntityToDomainConverter.convert(entity.getWeatherDataEntity()));
    }
}
