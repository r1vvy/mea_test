package com.meawallet.weatherapp.out;

import com.meawallet.weatherapp.core.port.out.OutGoingWeatherApiPort;
import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.out.config.WeatherApiConfig;
import com.meawallet.weatherapp.out.converter.HourlyWeatherDataDtoToWeatherDataDomainConverter;
import com.meawallet.weatherapp.out.dto.GetWeatherDataOutResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class GetWeatherDataByLatitudeAndLongitudeAdapterFromOutApi implements OutGoingWeatherApiPort {

    private final RestTemplate restTemplate;
    private final WeatherApiConfig weatherApiConfig;
    private final HourlyWeatherDataDtoToWeatherDataDomainConverter hourlyWeatherDataDtoToWeatherDataDomainConverter;


    @Override
    public List<WeatherData> getWeatherData(BigDecimal latitude, BigDecimal longitude) {
        try {
            var url = weatherApiConfig.getWeatherUrl()
                    .replace("{latitude}", latitude.toString())
                    .replace("{longitude}", longitude.toString());
            var response = restTemplate.getForEntity(url, GetWeatherDataOutResponse.class);

            log.debug("Received weather data from Weather API: {}", response);

            return response.getBody().timeseriesData()
                    .stream()
                    .map(hourlyWeatherDataDtoToWeatherDataDomainConverter::convert)
                    .collect(Collectors.toList());
        } catch (RestClientException restClientException) {
            log.error("Received error from Weather API: {}", restClientException.getMessage());
            throw new RuntimeException(restClientException);
        }
    }
}
