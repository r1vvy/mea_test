package com.meawallet.weatherapp.core.port.out;

import com.meawallet.weatherapp.domain.Location;

import java.math.BigDecimal;
import java.util.Optional;

public interface GetLocationByLatitudeAndLongitudePort {
   Optional<Location> getLocation(BigDecimal latitude, BigDecimal longitude);
}
