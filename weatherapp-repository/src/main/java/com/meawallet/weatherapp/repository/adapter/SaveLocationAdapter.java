package com.meawallet.weatherapp.repository.adapter;

import com.meawallet.weatherapp.core.port.out.SaveLocationPort;
import com.meawallet.weatherapp.domain.Location;
import com.meawallet.weatherapp.repository.converter.LocationDomainToEntityConverter;
import com.meawallet.weatherapp.repository.converter.LocationEntityToDomainConverter;
import com.meawallet.weatherapp.repository.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SaveLocationAdapter implements SaveLocationPort {
    private final LocationRepository locationRepository;
    private final LocationDomainToEntityConverter locationDomainToEntityConverter;
    private final LocationEntityToDomainConverter locationEntityToDomainConverter;

    @Override
    public Location save(Location location) {
        var locationEntity = locationDomainToEntityConverter.convert(location);
        var savedEntity = locationRepository.save(locationEntity);

        return locationEntityToDomainConverter.convert(savedEntity);
    }
}
