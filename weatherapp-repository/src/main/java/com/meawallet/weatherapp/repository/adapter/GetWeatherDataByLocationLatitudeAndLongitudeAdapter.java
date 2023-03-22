package com.meawallet.weatherapp.repository.adapter;

import com.meawallet.weatherapp.core.port.out.GetWeatherDataByLocationLatitudeAndLongitudeFromRepoPort;
import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.repository.converter.WeatherDataEntityToDomainConverter;
import com.meawallet.weatherapp.repository.entity.WeatherDataEntity;
import com.meawallet.weatherapp.repository.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@AllArgsConstructor
public class GetWeatherDataByLocationLatitudeAndLongitudeAdapter implements GetWeatherDataByLocationLatitudeAndLongitudeFromRepoPort {

    private final LocationRepository locationRepository;
    private final WeatherDataEntityToDomainConverter weatherDataEntityToDomainConverter;

    @Override
    public Optional<WeatherData> getWeatherData(BigDecimal latitude, BigDecimal longitude) {

        return locationRepository.findWeatherDataByLatitudeAndLongitude(latitude, longitude)
                .map(weatherDataEntityToDomainConverter::convert);
    }
}
