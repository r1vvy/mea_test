package com.meawallet.weatherapp.core.port.out;

import com.meawallet.weatherapp.domain.Location;
import com.meawallet.weatherapp.domain.WeatherData;

public interface UpdateLocationPort {

    void update(Location location);
}
