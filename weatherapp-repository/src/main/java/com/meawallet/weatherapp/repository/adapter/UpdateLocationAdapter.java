package com.meawallet.weatherapp.repository.adapter;

import com.meawallet.weatherapp.core.port.out.UpdateLocationPort;
import com.meawallet.weatherapp.domain.Location;
import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.repository.converter.LocationDomainToEntityConverter;
import com.meawallet.weatherapp.repository.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UpdateLocationAdapter implements UpdateLocationPort {
    private final LocationRepository locationRepository;
    private final LocationDomainToEntityConverter locationDomainToEntityConverter;

    @Override
    @Transactional
    public void update(Location location) {
        var entity = locationDomainToEntityConverter.convert(location);
        locationRepository.save(entity);

        log.debug("Location entity updated successfully: {}", entity);
    }
}
