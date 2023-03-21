package com.meawallet.weatherapp.in;

import com.meawallet.weatherapp.core.port.in.GetWeatherDataByLocationLatitudeAndLongitudeUseCase;
import com.meawallet.weatherapp.in.converter.WeatherDataToGetTemperatureInResponseConverter;
import com.meawallet.weatherapp.in.dto.GetTemperatureInResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/weather")
public class AppController {
    private final GetWeatherDataByLocationLatitudeAndLongitudeUseCase getWeatherDataByLocationLatitudeAndLongitudeUseCase;

    private final WeatherDataToGetTemperatureInResponseConverter weatherDataToGetTemperatureInResponseConverter;

    @GetMapping("")
    public ResponseEntity<GetTemperatureInResponse> findByLatitudeAndLongitude(@RequestParam(name = "lat") BigDecimal latitude, @RequestParam(name = "lon") BigDecimal longitude) {
        var weatherData = getWeatherDataByLocationLatitudeAndLongitudeUseCase.getWeatherData(latitude, longitude);
        var responseBody = weatherDataToGetTemperatureInResponseConverter.convert(weatherData);

        return ResponseEntity.ok().body(responseBody);
    }
}
