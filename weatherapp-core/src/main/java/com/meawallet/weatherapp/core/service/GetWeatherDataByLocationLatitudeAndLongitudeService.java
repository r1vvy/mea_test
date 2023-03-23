package com.meawallet.weatherapp.core.service;

import com.meawallet.weatherapp.core.exceptions.NoDataFoundException;
import com.meawallet.weatherapp.core.port.out.*;
import com.meawallet.weatherapp.core.port.in.GetWeatherDataByLocationLatitudeAndLongitudeUseCase;
import com.meawallet.weatherapp.core.port.out.GetWeatherDataFromOutWeatherApiPort;
import com.meawallet.weatherapp.core.utils.LatLongUtils;
import com.meawallet.weatherapp.domain.Location;
import com.meawallet.weatherapp.domain.WeatherData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class GetWeatherDataByLocationLatitudeAndLongitudeService implements GetWeatherDataByLocationLatitudeAndLongitudeUseCase {
    private final SaveLocationPort saveLocationPort;
    private final GetWeatherDataFromOutWeatherApiPort getWeatherDataFromOutWeatherApiPort;
    private final GetLocationByLatitudeAndLongitudePort getLocationByLatitudeAndLongitudePort;

    @Override
    public WeatherData getWeatherData(BigDecimal latitude, BigDecimal longitude) {
        latitude = latitude.setScale(LatLongUtils.LAT_LONG_SCALE, RoundingMode.HALF_UP);
        longitude = longitude.setScale(LatLongUtils.LAT_LONG_SCALE, RoundingMode.HALF_UP);

        var location = getLocationByLatitudeAndLongitudePort.getLocation(latitude, longitude);

        if (location.isPresent()) {
            return getWeatherDataForExistingLocation(location.get());
        } else {
            return getWeatherDataForNewLocation(latitude, longitude);
        }
    }

    private WeatherData getWeatherDataForExistingLocation(Location location) {
        var weatherData = location.weatherData();

        if (isWeatherDataFromCurrentHourUtc(weatherData)) {
            log.debug("Weather data found from cache: {}", weatherData);
            
            return weatherData;
        } else {
            weatherData = fetchWeatherDataFromOutWeatherApi(location.latitude(), location.longitude());
            
            updateLocationWithWeatherData(location, weatherData);
            log.debug("Weather data updated successfully: {}", weatherData);
            
            return weatherData;
        }
    }

    private WeatherData getWeatherDataForNewLocation(BigDecimal latitude, BigDecimal longitude) {
        var weatherData = fetchWeatherDataFromOutWeatherApi(latitude, longitude);

        var newLocation = Location.builder()
                .latitude(latitude)
                .longitude(longitude)
                .weatherData(weatherData)
                .build();

        var savedLocation = saveLocationPort.save(newLocation);
        log.debug("Weather data and location saved successfully: {}", savedLocation);

        return savedLocation.weatherData();
    }

    private void updateLocationWithWeatherData(Location location, WeatherData weatherData) {
        location.toBuilder()
                .weatherData(weatherData)
                .build();

        saveLocationPort.save(location);
    }

    private boolean isWeatherDataFromCurrentHourUtc(WeatherData weatherData) {
        return weatherData.timestamp().getHour() == ZonedDateTime.now(ZoneOffset.UTC).getHour();
    }

    private WeatherData fetchWeatherDataFromOutWeatherApi(BigDecimal latitude, BigDecimal longitude) {
        WeatherData weatherData;
        weatherData = getWeatherDataFromOutWeatherApiPort.getWeatherData(latitude, longitude)
                .stream()
                .filter(incomingData -> incomingData.timestamp().isAfter(ZonedDateTime.now(ZoneOffset.UTC).minusHours(1)))
                .findAny()
                .orElseThrow(() -> new NoDataFoundException("No weather data found within the last hour from the Outgoing Weather API"));

        log.debug("Weather data found from Outgoing Weather API: {}", weatherData);

        return weatherData;
    }
}
