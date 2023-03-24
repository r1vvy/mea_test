package com.meawallet.weatherapp.in;

import com.meawallet.weatherapp.core.exception.NoDataFoundException;
import com.meawallet.weatherapp.core.exception.NotImplementedException;
import com.meawallet.weatherapp.core.exception.OutgoingWeatherApiException;
import com.meawallet.weatherapp.core.exception.WeatherDataFetchException;
import com.meawallet.weatherapp.core.port.in.GetWeatherDataByLocationLatitudeAndLongitudeUseCase;
import com.meawallet.weatherapp.in.converter.WeatherDataToGetTemperatureInResponseConverter;
import com.meawallet.weatherapp.in.dto.ErrorResponse;
import com.meawallet.weatherapp.in.dto.GetTemperatureInResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/weather")
public class AppController {
    private final GetWeatherDataByLocationLatitudeAndLongitudeUseCase getWeatherDataByLocationLatitudeAndLongitudeUseCase;

    private final WeatherDataToGetTemperatureInResponseConverter weatherDataToGetTemperatureInResponseConverter;

    @GetMapping("")
    public ResponseEntity<GetTemperatureInResponse> findByLatitudeAndLongitude(@RequestParam(name = "lat") BigDecimal latitude, @RequestParam(name = "lon") BigDecimal longitude) {
        log.debug("Recieved find weather data by latitude and longitude request: latitude={}, longitude={}",latitude, longitude );

        var weatherData = getWeatherDataByLocationLatitudeAndLongitudeUseCase.getWeatherData(latitude, longitude);
        var responseBody = weatherDataToGetTemperatureInResponseConverter.convert(weatherData);

        return ResponseEntity.ok().body(responseBody);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception e) {
        return new ErrorResponse(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleWeatherDataFetchException(WeatherDataFetchException e) {
        var errorMessage = e.getMessage();
        return new ErrorResponse(errorMessage, LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleNoDataFoundException(NoDataFoundException e) {
        var errorMessage = e.getMessage();
        return new ErrorResponse(errorMessage, LocalDateTime.now());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ErrorResponse handleNotImplementedException(NotImplementedException e) {
        var errorMessage = e.getMessage();
        return new ErrorResponse(errorMessage, LocalDateTime.now());
    }

    private String errorDescription(FieldError fieldError) {
        return "Field: " + fieldError.getField() + ", description: " + fieldError.getDefaultMessage();
    }
}
