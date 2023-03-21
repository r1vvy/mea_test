package com.meawallet.weatherapp.out;

import com.meawallet.weatherapp.out.config.WeatherApiConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class GetTemperatureByLatitudeAndLongtitudeAdapter {

    private final RestTemplate restTemplate;
    private final WeatherApiConfig weatherApiConfig;



}
