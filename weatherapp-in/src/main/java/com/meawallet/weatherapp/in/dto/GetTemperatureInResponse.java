package com.meawallet.weatherapp.in.dto;

import java.math.BigDecimal;

public record GetTemperatureInResponse(
        BigDecimal temperature
) {
}
