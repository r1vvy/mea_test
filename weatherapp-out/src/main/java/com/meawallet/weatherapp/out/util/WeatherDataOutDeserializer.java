package com.meawallet.weatherapp.out.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.meawallet.weatherapp.domain.WeatherData;
import com.meawallet.weatherapp.out.dto.GetWeatherDataOutResponse;
import com.meawallet.weatherapp.out.dto.WeatherDataOutDto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherDataOutDeserializer extends JsonDeserializer<GetWeatherDataOutResponse> {
    @Override
    public GetWeatherDataOutResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
    }
}
