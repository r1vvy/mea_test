package com.meawallet.weatherapp.core.exceptions;

import org.springframework.stereotype.Component;

public class NotImplementedException extends RuntimeException {
    public NotImplementedException(String message) {
        super(message);
    }
}
