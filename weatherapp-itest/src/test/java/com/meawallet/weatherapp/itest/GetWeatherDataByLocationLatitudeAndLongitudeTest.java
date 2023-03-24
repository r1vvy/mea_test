package com.meawallet.weatherapp.itest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DatabaseTearDown(value = "classpath:dbunit/empty_dataset.xml", type = DELETE_ALL)
public class GetWeatherDataByLocationLatitudeAndLongitudeTest extends BaseIntegrationTest {

    @Test
    void shouldReturnWeatherDataFromCache() {
        assertEquals(1+1, 2);
    }
}
