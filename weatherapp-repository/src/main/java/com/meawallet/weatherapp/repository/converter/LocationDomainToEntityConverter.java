package com.meawallet.weatherapp.repository.converter;

import com.meawallet.weatherapp.domain.Location;
import com.meawallet.weatherapp.repository.entity.LocationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class LocationDomainToEntityConverter {
    private final WeatherDataDomainToEntityConverter weatherDataDomainToEntityConverter;

    public LocationEntity convert(Location location) {
        var weatherDataEntity = weatherDataDomainToEntityConverter.convert(location.weatherData());

        var locationEntity = LocationEntity.builder()
                .id(location.id())
                .latitude(location.latitude())
                .longitude(location.longitude())
                .weatherDataEntity(weatherDataEntity)
                .build();

        return locationEntity;
    }
}
