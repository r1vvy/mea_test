package com.meawallet.weatherapp.core.port.out;

import com.meawallet.weatherapp.domain.Location;

public interface SaveLocationPort {
    Location save(Location location);
}
