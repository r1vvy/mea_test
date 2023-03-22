package com.meawallet.weatherapp.core.service;

import com.meawallet.weatherapp.core.exceptions.NotImplementedException;
import com.meawallet.weatherapp.core.port.out.GetLocationByLatitudeAndLongitudePort;
import com.meawallet.weatherapp.core.port.in.GetWeatherDataByLocationLatitudeAndLongitudeUseCase;
import com.meawallet.weatherapp.core.port.out.GetWeatherDataFromOutGoingWeatherApiPort;
import com.meawallet.weatherapp.core.port.out.GetWeatherDataByLocationLatitudeAndLongitudeFromRepoPort;
import com.meawallet.weatherapp.core.port.out.SaveLocationPort;
import com.meawallet.weatherapp.core.port.out.SaveWeatherDataPort;
import com.meawallet.weatherapp.core.utils.LatLongMathContext;
import com.meawallet.weatherapp.domain.Location;
import com.meawallet.weatherapp.domain.WeatherData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetWeatherDataByLocationLatitudeAndLongitudeService implements GetWeatherDataByLocationLatitudeAndLongitudeUseCase {
    private final SaveLocationPort saveLocationPort;
    private final SaveWeatherDataPort saveWeatherDataPort;
    private final GetWeatherDataByLocationLatitudeAndLongitudeFromRepoPort getWeatherDataByLocationLatitudeAndLongitudeFromRepoPort;
    private final GetWeatherDataFromOutGoingWeatherApiPort getWeatherDataFromOutGoingWeatherApiPort;
    private final GetLocationByLatitudeAndLongitudePort getLocationByLatitudeAndLongitudePort;

    // TODO
    @Override
    public WeatherData getWeatherData(BigDecimal latitude, BigDecimal longitude) {
        throw new NotImplementedException("Not implemented yet!");
        /*
        latitude.round(LatLongMathContext.LAT_LONG_PRECISION);
        longitude.round(LatLongMathContext.LAT_LONG_PRECISION);

        var location = getLocationByLatitudeAndLongitudePort.getLocation(latitude, longitude);

        if(location.isPresent()) {
            WeatherData weatherData;
            var currentTime = ZonedDateTime.now(ZoneOffset.UTC);

            weatherData= location.get().weatherData();

            if(weatherData.timestamp().getHour() == currentTime.getHour()) {
                return weatherData;
            }
            else {
                weatherData = getWeatherDataFromOutGoingWeatherApiPort.getWeatherData(latitude, longitude)
                        .stream()
                        .filter(incomingData -> incomingData.timestamp().isAfter(ZonedDateTime.now(ZoneOffset.UTC).minusHours(1)))
                        .findAny()
                        .orElseThrow(() -> new RuntimeException("Request failed!"));

                var savedWeatherData = saveWeatherDataPort.save(weatherData);
                location.get()
                        .toBuilder()
                        .weatherData(savedWeatherData);
            }
        }
        saveLocationPort(location.get());

        return null;
        */
    }
}
