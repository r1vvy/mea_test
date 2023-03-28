package com.meawallet.weatherapp.domain;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static com.meawallet.weatherapp.domain.utils.ZonedDateTimeParser.ZONED_DATE_TIME;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LocationTest {

    @Test
    protected void shouldCreateLocation() {
        Integer id = 1;
        BigDecimal latitude = new BigDecimal("10");
        BigDecimal longitude = new BigDecimal("10");
        WeatherData weatherData = weatherData();

        Location location = Location.builder()
                .id(id)
                .latitude(latitude)
                .longitude(longitude)
                .weatherData(weatherData)
                .build();

        assertEquals(id, location.id());
        assertEquals(latitude, latitude);
        assertEquals(longitude, longitude);
        assertEquals(weatherData, location.weatherData());
    }

    @Test
    protected void locationToBuilder() {
        var location = location();

        Integer id = 2;
        BigDecimal latitude = new BigDecimal("10.5");
        BigDecimal longitude = new BigDecimal("10.5");
        var weatherData = WeatherData.builder()
                .id(2)
                .airTemperature(new BigDecimal("-10"))
                .timestamp(ZONED_DATE_TIME)
                .build();

        location = location.toBuilder()
                .id(id)
                .latitude(latitude)
                .longitude(longitude)
                .weatherData(weatherData)
                .build();

        assertEquals(id, location.id());
        assertEquals(latitude, location.latitude());
        assertEquals(longitude, location.longitude());
        assertEquals(weatherData, location.weatherData());
    }

    Location location() {
        return Location.builder()
                .id(1)
                .latitude(new BigDecimal("10"))
                .longitude(new BigDecimal("10"))
                .weatherData(weatherData())
                .build();
    }

    WeatherData weatherData() {
        return WeatherData.builder()
                .id(1)
                .airTemperature(new BigDecimal("10"))
                .timestamp(ZONED_DATE_TIME)
                .build();
    }
}
