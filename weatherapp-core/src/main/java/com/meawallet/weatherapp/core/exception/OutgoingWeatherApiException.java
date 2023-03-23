package com.meawallet.weatherapp.core.exception;

public class OutgoingWeatherApiException extends RuntimeException {

    public OutgoingWeatherApiException(String message) {
        super(message);
    }

    public OutgoingWeatherApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
