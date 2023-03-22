package com.meawallet.weatherapp.repository.converter;

import com.meawallet.weatherapp.domain.Location;
import com.meawallet.weatherapp.repository.entity.LocationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class LocationEntityToDomainConverter {
    private final WeatherDataEntityToDomainConverter weatherDataEntityToDomainConverter;

    public Location convert(LocationEntity entity) {
        var builder = Location
                .builder()
                .id(entity.getId())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude());

        Optional.ofNullable(entity.getWeatherDataEntity())
                .map(weatherDataEntityToDomainConverter::convert)
                .ifPresent(builder::weatherData);

        return builder.build();
    }

}
