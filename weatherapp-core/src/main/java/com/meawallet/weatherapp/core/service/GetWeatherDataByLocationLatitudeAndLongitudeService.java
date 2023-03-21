package com.meawallet.weatherapp.core.service;

import com.meawallet.weatherapp.core.port.in.GetWeatherDataByLocationLatitudeAndLongitudeUseCase;
import com.meawallet.weatherapp.core.port.out.OutGoingWeatherApiPort;
import com.meawallet.weatherapp.core.port.out.GetWeatherDataByLocationLatitudeAndLongitudeFromRepoPort;
import com.meawallet.weatherapp.core.port.out.SaveLocationPort;
import com.meawallet.weatherapp.core.port.out.SaveWeatherDataPort;
import com.meawallet.weatherapp.domain.Location;
import com.meawallet.weatherapp.domain.WeatherData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetWeatherDataByLocationLatitudeAndLongitudeService implements GetWeatherDataByLocationLatitudeAndLongitudeUseCase {
    private final SaveLocationPort saveLocationPort;
    private final SaveWeatherDataPort saveWeatherDataPort;
    private final GetWeatherDataByLocationLatitudeAndLongitudeFromRepoPort getWeatherDataByLocationLatitudeAndLongitudeFromRepoPort;
    private final OutGoingWeatherApiPort outGoingWeatherApiPort;
    private final GetLocationByLatitudeAndLongitudePort getLocationByLatitudeAndLongitudePort;

    @Override
    public WeatherData getWeatherData(BigDecimal latitude, BigDecimal longitude) {
        Optional<WeatherData> weatherDataOptional = getWeatherDataByLocationLatitudeAndLongitudeFromRepoPort.getWeatherData(latitude, longitude);
        WeatherData weatherData;

        if (weatherDataOptional.isEmpty()) {
            weatherData = outGoingWeatherApiPort.getWeatherData(latitude, longitude)
                    .stream()
                    .filter(incomingData -> incomingData.timestamp().isAfter(ZonedDateTime.now(ZoneOffset.UTC).minusHours(1)))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No weather data found for the given location"));

            Optional<Location> locationOptional = getLocationByLatitudeAndLongitudePort.getLocation(latitude, longitude);
            if (locationOptional.isEmpty()) {
                var location = Location.builder()
                        .latitude(latitude)
                        .longitude(longitude)
                        .build();

                saveLocationPort.saveLocation(location);
            }

            saveWeatherDataPort.saveWeatherData(weatherData);
        } else {

            weatherData = weatherDataOptional.orElseThrow(() -> new RuntimeException("No weather data found for the given location"));
        }

        return weatherData;
    }
}
