package com.meawallet.weatherapp.core.service;

import com.meawallet.weatherapp.core.exception.NoDataFoundException;
import com.meawallet.weatherapp.core.exception.OutgoingWeatherApiException;
import com.meawallet.weatherapp.core.exception.WeatherDataFetchException;
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
import java.time.DateTimeException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class GetWeatherDataByLocationLatitudeAndLongitudeService implements GetWeatherDataByLocationLatitudeAndLongitudeUseCase {
    private final SaveLocationPort saveLocationPort;
    private final SaveWeatherDataPort saveWeatherDataPort;
    private final UpdateWeatherDataPort updateWeatherDataPort;
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
            log.debug("Weather data found in cache: {}", weatherData);
            
            return weatherData;

        }
        weatherData = fetchWeatherDataFromOutWeatherApi(location.latitude(), location.longitude());
        weatherData = weatherData.toBuilder()
                .id(location.weatherData().id())
                .build();

        try {
            updateWeatherDataPort.update(weatherData);
            log.debug("Weather data updated successfully: {}", weatherData);
        } catch(NoDataFoundException exc) {
            log.debug("Failed to update weather data: {}", exc.getMessage());
        }

        return weatherData;
    }

    private WeatherData getWeatherDataForNewLocation(BigDecimal latitude, BigDecimal longitude) {
        var weatherData = fetchWeatherDataFromOutWeatherApi(latitude, longitude);
        var savedWeatherData = saveWeatherDataPort.save(weatherData);

        var newLocation = Location.builder()
                .latitude(latitude)
                .longitude(longitude)
                .weatherData(savedWeatherData)
                .build();

        var savedLocation = saveLocationPort.save(newLocation);

        return savedLocation.weatherData();
    }

    private WeatherData fetchWeatherDataFromOutWeatherApi(BigDecimal latitude, BigDecimal longitude) {
        try {
            var weatherData = getWeatherDataFromOutWeatherApiPort.getWeatherData(latitude, longitude)
                    .stream()
                    .filter(incomingData -> incomingData.timestamp().isAfter(ZonedDateTime.now(ZoneOffset.UTC).minusHours(1)))
                    .findAny()
                    .orElseThrow(() -> new NoDataFoundException("No weather data found within the last hour from the Outgoing Weather API"));

            log.debug("Weather data found from Outgoing Weather API: {}", weatherData);

            return weatherData;
        } catch(OutgoingWeatherApiException | NoDataFoundException exc) {
            throw new WeatherDataFetchException("Failed to fetch Weather Data from Outgoing Weather API ");
        }
    }

    private boolean isWeatherDataFromCurrentHourUtc(WeatherData weatherData) {
        return weatherData.timestamp().getHour() == ZonedDateTime.now(ZoneOffset.UTC).getHour();
    }
}
