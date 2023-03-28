package com.meawallet.weatherapp.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static com.meawallet.weatherapp.domain.utils.ZonedDateTimeParser.ZONED_DATE_TIME;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class WeatherDataTest {

    @Test
    protected void shouldCreateWeatherData() {
        Integer id = 1;
        BigDecimal airTemperature = new BigDecimal("10");
        ZonedDateTime timestamp = ZONED_DATE_TIME;

        var weatherData = WeatherData.builder()
                .id(id)
                .airTemperature(airTemperature)
                .timestamp(timestamp)
                .build();

        assertEquals(id, weatherData.id());
        assertEquals(airTemperature, weatherData.airTemperature());
        assertEquals(timestamp, weatherData.timestamp());
    }

    @Test
    protected void weatherDataToBuilder() {
        var weatherData = weatherData();

        Integer id = 2;
        BigDecimal airTemperature = new BigDecimal("11");
        ZonedDateTime timestamp = ZONED_DATE_TIME.plusHours(1);

        weatherData = WeatherData.builder()
                .id(id)
                .airTemperature(airTemperature)
                .timestamp(timestamp)
                .build();

        assertEquals(id, weatherData.id());
        assertEquals(airTemperature, weatherData.airTemperature());
        assertEquals(timestamp, weatherData.timestamp());
    }


    WeatherData weatherData() {
        return WeatherData.builder()
                .id(1)
                .airTemperature(new BigDecimal("10"))
                .timestamp(ZONED_DATE_TIME)
                .build();
    }
}
