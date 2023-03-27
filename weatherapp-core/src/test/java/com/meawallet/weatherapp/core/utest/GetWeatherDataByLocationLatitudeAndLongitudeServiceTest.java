package com.meawallet.weatherapp.core.utest;

import com.meawallet.weatherapp.core.exception.NoDataFoundException;
import com.meawallet.weatherapp.core.port.out.*;
import com.meawallet.weatherapp.core.service.GetWeatherDataByLocationLatitudeAndLongitudeService;
import com.meawallet.weatherapp.domain.Location;
import com.meawallet.weatherapp.domain.WeatherData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetWeatherDataByLocationLatitudeAndLongitudeServiceTest {
    @Mock
    private SaveLocationPort saveLocationPort;
    @Mock
    private SaveWeatherDataPort saveWeatherDataPort;
    @Mock
    private UpdateWeatherDataPort updateWeatherDataPort;
    @Mock
    private GetWeatherDataFromOutWeatherApiPort getWeatherDataFromOutWeatherApiPort;
    @Mock
    private GetLocationByLatitudeAndLongitudePort getLocationByLatitudeAndLongitudePort;

    @InjectMocks
    private GetWeatherDataByLocationLatitudeAndLongitudeService service;

    @Test
    public void shouldReturnWeatherDataFromExistingLocationFromCachedData() {
        Location location = location(newWeatherData());
        var latitude = location.latitude();
        var longitude = location.longitude();

        when(getLocationByLatitudeAndLongitudePort.getLocation(latitude, longitude))
                .thenReturn(Optional.of(location));

        var result = service.getWeatherData(latitude, longitude);
        assertNotNull(result);
        assertEquals(location.weatherData().airTemperature(), result.airTemperature());

        verify(getLocationByLatitudeAndLongitudePort, times(1)).getLocation(
                latitude, longitude);
        verifyNoInteractions(saveLocationPort, saveWeatherDataPort, getWeatherDataFromOutWeatherApiPort);
    }

    @Test
    public void shouldReturnWeatherDataFromWeatherApiWhenCachedDataIsNotUpdated() {
        Location location = location(oldWeatherData());
        var latitude = location.latitude();
        var longitude = location.longitude();

        when(getLocationByLatitudeAndLongitudePort.getLocation(latitude, longitude))
                .thenReturn(Optional.of(location));
        when(getWeatherDataFromOutWeatherApiPort.getWeatherData(latitude, longitude))
                .thenReturn(Collections.singletonList(newWeatherData()));


        var result = service.getWeatherData(latitude, longitude);

        assertNotNull(result);
        assertEquals(location.weatherData().airTemperature(), result.airTemperature());
        verify(getLocationByLatitudeAndLongitudePort, times(1)).getLocation(
                latitude, longitude);
        verify(getWeatherDataFromOutWeatherApiPort, times(1)).getWeatherData(
                latitude, longitude);
        verifyNoInteractions(saveLocationPort, saveWeatherDataPort);
    }

    @Test
    public void shouldReturnSavedWeatherDataForNewLocation() {
        BigDecimal latitude = new BigDecimal("40.7128");
        BigDecimal longitude = new BigDecimal("-74.0060");

        when(getWeatherDataFromOutWeatherApiPort.getWeatherData(latitude, longitude))
                .thenReturn(Collections.singletonList(newWeatherData()));
        when(saveWeatherDataPort.save(any(WeatherData.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
        when(saveLocationPort.save(any(Location.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        var result = service.getWeatherData(latitude, longitude);

        assertNotNull(result);
        assertEquals(newWeatherData().airTemperature(), result.airTemperature());
        verify(getWeatherDataFromOutWeatherApiPort, times(1)).getWeatherData(
                latitude, longitude);
        verify(saveWeatherDataPort, times(1)).save(any(WeatherData.class));
        verify(saveLocationPort, times(1)).save(any(Location.class));
        verifyNoMoreInteractions(getWeatherDataFromOutWeatherApiPort, saveWeatherDataPort, saveLocationPort);
    }


    @Test
    public void shouldThrowNoDataFoundExceptionOnGetWeatherDataForExistingLocation() {
        Location location = locationWithoutWeatherData();

        Mockito.when(getLocationByLatitudeAndLongitudePort.getLocation(location.latitude(), location.longitude()))
                .thenThrow(new NoDataFoundException("No weather data found"));


        assertThrows(NoDataFoundException.class, () -> {
            service.getWeatherData(location.latitude(), location.longitude());
        });
    }

    private Location location(WeatherData weatherData) {
        return Location.builder()
                .id(1)
                .latitude(new BigDecimal("40.7128"))
                .longitude(new BigDecimal("-74.0060"))
                .weatherData(weatherData)
                .build();
    }

    private Location locationWithoutWeatherData() {
        return Location.builder()
                .id(1)
                .latitude(new BigDecimal("40.7128"))
                .longitude(new BigDecimal("-74.0060"))
                .build();
    }

    private WeatherData newWeatherData() {
        return  WeatherData.builder()
                .id(1)
                .airTemperature(new BigDecimal("10.0"))
                .timestamp(ZonedDateTime.now(ZoneOffset.UTC))
                .build();
    }

    private WeatherData oldWeatherData() {
        return WeatherData.builder()
                .id(1)
                .airTemperature(new BigDecimal("10.0"))
                .timestamp(ZonedDateTime.now(ZoneOffset.UTC).minusHours(1))
                .build();
    }
}
