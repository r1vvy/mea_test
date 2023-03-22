package com.meawallet.weatherapp.in.converter;

import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.in.dto.GetTemperatureInResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class WeatherDataToGetTemperatureInResponseConverter {
        public GetTemperatureInResponse convert(WeatherData weatherData) {
            return new GetTemperatureInResponse(weatherData.airTemperature());
        }
}
