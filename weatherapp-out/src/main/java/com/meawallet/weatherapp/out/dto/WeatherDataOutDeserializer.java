package com.meawallet.weatherapp.out.dto;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.meawallet.weatherapp.domain.WeatherData;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherDataOutDeserializer extends JsonDeserializer<GetWeatherDataOutResponse> {
    @Override
    public GetWeatherDataOutResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        List<WeatherDataOutDto> hourlyWeatherDataList = new ArrayList<>();

        JsonNode timeseriesNode = node.get("properties").get("timeseries");
        if (timeseriesNode.isArray()) {
            for (JsonNode tsNode : timeseriesNode) {
                String time = tsNode.get("time").asText();
                BigDecimal airTemperature = tsNode.get("data").get("instant").get("details").get("air_temperature").decimalValue();
                hourlyWeatherDataList.add(new WeatherDataOutDto(ZonedDateTime.parse(time), airTemperature));
            }
        }

        return new GetWeatherDataOutResponse(hourlyWeatherDataList);
    }
}
