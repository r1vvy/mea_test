package com.meawallet.weatherapp.repository.adapter;

import com.meawallet.weatherapp.core.port.out.SaveLocationPort;
import com.meawallet.weatherapp.domain.Location;
import com.meawallet.weatherapp.repository.converter.LocationDomainToEntityConverter;
import com.meawallet.weatherapp.repository.converter.LocationEntityToDomainConverter;
import com.meawallet.weatherapp.repository.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SaveLocationAdapter implements SaveLocationPort {
    private final LocationRepository locationRepository;
    private final LocationDomainToEntityConverter locationDomainToEntityConverter;
    private final LocationEntityToDomainConverter locationEntityToDomainConverter;

    @Override
    public Location save(Location location) {
        var locationEntity = locationDomainToEntityConverter.convert(location);
        locationRepository.save(locationEntity);
        var savedEntity = locationEntityToDomainConverter.convert(locationEntity);

        log.debug("Location entity saved successfully: {}", savedEntity);

        return savedEntity;
    }
}
