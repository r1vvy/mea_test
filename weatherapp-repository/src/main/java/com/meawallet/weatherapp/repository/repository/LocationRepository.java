package com.meawallet.weatherapp.repository.repository;

import com.meawallet.weatherapp.repository.entity.LocationEntity;
import com.meawallet.weatherapp.repository.entity.WeatherDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {
    Optional<LocationEntity> findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);
}
