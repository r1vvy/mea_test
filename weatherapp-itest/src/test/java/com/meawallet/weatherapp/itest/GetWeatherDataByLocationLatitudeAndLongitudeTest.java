package com.meawallet.weatherapp.itest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DatabaseTearDown(value = "classpath:dbunit/empty_dataset.xml", type = DELETE_ALL)
public class GetWeatherDataByLocationLatitudeAndLongitudeTest extends BaseIntegrationTest {

//    @Test
    @DatabaseSetup(value = "classpath:dbunit/findWeatherDataSuccess.xml")
    @ExpectedDatabase(value = "classpath:dbunit/findWeatherDataSuccess.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void shouldReturnWeatherDataFromCache() throws Exception {
        BigDecimal latitude = new BigDecimal("1");
        BigDecimal longitude = new BigDecimal("1");


        var response = readJson("GetWeatherDataFromCacheSuccess.json");
        var result = readJson("TemperatureResponseSuccess.json");

        mvc.perform(MockMvcRequestBuilders.get("/weather", latitude, longitude))
                .andExpect(content().json(response))
                .andExpect(status().isOk());
    }
}
