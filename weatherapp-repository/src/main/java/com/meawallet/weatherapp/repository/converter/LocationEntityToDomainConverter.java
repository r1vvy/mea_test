package com.meawallet.weatherapp.repository.converter;

import com.meawallet.weatherapp.domain.Location;
import com.meawallet.weatherapp.repository.entity.LocationEntity;
import org.springframework.stereotype.Component;

@Component
public class LocationEntityToDomainConverter {
    public Location convert(LocationEntity entity) {

        return Location.builder()
                .id(entity.getId())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();
    }

}
