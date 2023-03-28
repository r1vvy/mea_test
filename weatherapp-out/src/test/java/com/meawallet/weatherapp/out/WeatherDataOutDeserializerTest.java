package com.meawallet.weatherapp.out;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.meawallet.weatherapp.core.exception.OutgoingWeatherApiException;
import com.meawallet.weatherapp.out.dto.GetWeatherDataOutResponse;
import com.meawallet.weatherapp.out.dto.WeatherDataOutDto;
import com.meawallet.weatherapp.out.util.WeatherDataOutDeserializer;
import com.meawallet.weatherapp.out.utils.ZonedDateTimeParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static com.meawallet.weatherapp.out.utils.ZonedDateTimeParser.ZONED_DATE_TIME;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WeatherDataOutDeserializerTest {

    @Test
    protected void shouldDeserializeIfValidInputAndReturnExpectedOutput() throws Exception {
        var timeseries = ZONED_DATE_TIME;
        var airTemperature = new BigDecimal("20.1");
        String jsonInput = "{" +
                "\"properties\":" +
                "{\"timeseries\":" +
                "[{\"time\":\"2023-03-28T06:00:00Z\"," +
                "\"data\":" +
                "{\"air_temperature\":20.1}}" +
                "]}" +
                "}";

        ObjectMapper objectMapper = new ObjectMapper();
        GetWeatherDataOutResponse response = objectMapper.readValue(jsonInput, GetWeatherDataOutResponse.class);

        assertEquals(weatherDataOutDto(), response.timeseriesData().get(0));

        WeatherDataOutDto weatherDataOutDto = response.timeseriesData().get(0);
        assertEquals(timeseries, weatherDataOutDto.time());
        assertEquals(airTemperature, weatherDataOutDto.airTemperature());
    }

    WeatherDataOutDto weatherDataOutDto() {
        return WeatherDataOutDto.builder()
                .time(ZONED_DATE_TIME)
                .airTemperature(new BigDecimal("20.1"))
                .build();
    }

}
