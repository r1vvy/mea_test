package com.meawallet.weatherapp.repository.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static com.meawallet.weatherapp.repository.utils.ZonedDateTimeParser.ZONED_DATE_TIME;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WeatherDataEntityTests {

    @Test
    protected void shouldCreateWeatherDataEntityFromConstructor() {
        Integer id = 1;
        ZonedDateTime timestamp = ZONED_DATE_TIME;
        BigDecimal airTemperature = new BigDecimal("20.1");

        var weatherData = weatherDataEntityFromConstructor();

        assertEquals(id, weatherData.getId());
        assertEquals(timestamp, weatherData.getTimestamp());
        assertEquals(airTemperature, weatherData.getAirTemperature());
    }

    @Test
    protected void shouldCreateWeatherDataEntityFromBuilder() {
        Integer id = 1;
        ZonedDateTime timestamp = ZONED_DATE_TIME;
        BigDecimal airTemperature = new BigDecimal("20.1");

        var weatherData = weatherDataEntityFromConstructor();

        assertEquals(id, weatherData.getId());
        assertEquals(timestamp, weatherData.getTimestamp());
        assertEquals(airTemperature, weatherData.getAirTemperature());
    }

    private WeatherDataEntity weatherDataEntityFromConstructor() {
        return new WeatherDataEntity(
                1,
                ZONED_DATE_TIME,
                new BigDecimal("20.1")
        );
    }

    private WeatherDataEntity weatherDataEntityFromBuilder() {
        return WeatherDataEntity.builder()
                .id(1)
                .timestamp(ZONED_DATE_TIME)
                .airTemperature(new BigDecimal("20.1"))
                .build();
    }
}
