package com.meawallet.weatherapp.repository.adapter;

import com.meawallet.weatherapp.core.port.out.GetLocationByLatitudeAndLongitudePort;
import com.meawallet.weatherapp.core.port.out.GetWeatherDataByLocationLatitudeAndLongitudeFromRepoPort;
import com.meawallet.weatherapp.domain.Location;
import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.repository.converter.LocationDomainToEntityConverter;
import com.meawallet.weatherapp.repository.converter.LocationEntityToDomainConverter;
import com.meawallet.weatherapp.repository.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@AllArgsConstructor
public class GetLocationByLatitudeAndLongitudeAdapter implements GetLocationByLatitudeAndLongitudePort {
    private final LocationRepository locationRepository;
    private final LocationEntityToDomainConverter locationEntityToDomainConverter;

    @Override
    public Optional<Location> getLocation(BigDecimal latitude, BigDecimal longitude) {

         return locationRepository.findByLatitudeAndLongitude(latitude, longitude)
                .map(locationEntityToDomainConverter::convert);
    }
}
