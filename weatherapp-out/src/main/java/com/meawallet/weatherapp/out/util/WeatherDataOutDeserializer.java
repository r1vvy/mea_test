package com.meawallet.weatherapp.out.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.meawallet.weatherapp.out.dto.GetWeatherDataOutResponse;
import com.meawallet.weatherapp.out.dto.WeatherDataOutDto;
import com.meawallet.weatherapp.core.exception.OutgoingWeatherApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WeatherDataOutDeserializer extends JsonDeserializer<GetWeatherDataOutResponse> {
    @Override
    public GetWeatherDataOutResponse deserialize(JsonParser p, DeserializationContext ctxt) {

        try {
            JsonNode node = p.getCodec().readTree(p);
            List<WeatherDataOutDto> hourlyWeatherDataList = new ArrayList<>();

            JsonNode timeseriesNode = node.get("properties").get("timeseries");

            timeseriesNode.forEach(tsNode -> {
                ZonedDateTime time = ZonedDateTime.parse(tsNode.get("time").asText());
                BigDecimal airTemperature = tsNode.findValue("air_temperature").decimalValue();
                hourlyWeatherDataList.add(WeatherDataOutDto.builder()
                        .time(time)
                        .airTemperature(airTemperature)
                        .build()
                );
            });

            return new GetWeatherDataOutResponse(hourlyWeatherDataList);
        } catch(IOException | DateTimeParseException exc) {
            log.debug("Failed to deserialize Outgoing Weather API response: {}", exc.getMessage());
            throw new OutgoingWeatherApiException("Failed to deserialize Outgoing Weather API response");
        }
    }
}
