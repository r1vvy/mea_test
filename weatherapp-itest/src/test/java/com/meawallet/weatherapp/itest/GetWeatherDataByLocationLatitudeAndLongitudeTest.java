package com.meawallet.weatherapp.itest;


import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.meawallet.weatherapp.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import java.math.BigDecimal;

import static com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import(TestConfig.class)
@DatabaseTearDown(value = "classpath:dbunit/empty_dataset.xml", type = DELETE_ALL)
@DatabaseSetup("classpath:dbunit/findWeatherDataSuccess.xml")
public class GetWeatherDataByLocationLatitudeAndLongitudeTest extends BaseIntegrationTest {
    /*
    @Test
    void shouldReturnWeatherData() throws Exception{
        var response = readJson("getWeatherDataByLocationLatitudeAndLongitudeResponseSuccess.json");

        mvc.perform(get("/weather?lat=1.0&lon=1.0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(response))
                .andExpect(status().isOk());
    }
    */
}
