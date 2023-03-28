package com.meawallet.weatherapp.repository.entity;

import com.meawallet.weatherapp.domain.WeatherData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LocationEntityTest {
    @Mock
    WeatherData weatherData;

    @Test
    protected void shouldCreateALocationEntityFromConstructor() {
        Integer id = 1;
        var latitude = new BigDecimal("10");
        var longitude = new BigDecimal("10");

        var locationEntity = locationEntityFromConstructor(null);

        assertEquals(id, locationEntity.getId());
        assertEquals(latitude, locationEntity.getLatitude());
        assertEquals(longitude, locationEntity.getLongitude());
        assertEquals(null, locationEntity.getWeatherDataEntity());
    }

    @Test
    protected void shouldCreateALocationEntityFromBuilder() {
        Integer id = 1;
        var latitude = new BigDecimal("10");
        var longitude = new BigDecimal("10");

        var locationEntity = locationEntityFromBuilder(null);

        assertEquals(id, locationEntity.getId());
        assertEquals(latitude, locationEntity.getLatitude());
        assertEquals(longitude, locationEntity.getLongitude());
        assertEquals(null, locationEntity.getWeatherDataEntity());
    }

    private LocationEntity locationEntityFromConstructor(WeatherDataEntity weatherData) {

        return new LocationEntity(
                1,
                new BigDecimal("10"),
                new BigDecimal("10"),
                weatherData
        );
    }

    private LocationEntity locationEntityFromBuilder(WeatherDataEntity weatherData) {

        return LocationEntity.builder()
                .id(1)
                .latitude(new BigDecimal("10"))
                .longitude(new BigDecimal("10"))
                .weatherDataEntity(weatherData)
                .build();
    }
}
