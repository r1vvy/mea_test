package com.meawallet.weatherapp.itest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.meawallet.weatherapp.app.WeatherApplication;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;

@TestExecutionListeners(value = {
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@SpringBootTest(classes = WeatherApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableConfigurationProperties
@TestPropertySource(locations = "classpath:application-itest.yml")
@ActiveProfiles("itest")
public class BaseIntegrationTest {

    @Autowired
    protected MockMvc mvc;

    protected static WireMockServer wireMockServer = new WireMockServer(20000);

    @BeforeAll
    static void beforeAll() {
        wireMockServer.start();
    }

    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }

    @BeforeEach
    void setUp() {
        wireMockServer.resetAll();
    }

    protected String readJson(String jsonName) {
        var resource = BaseIntegrationTest.class.getResourceAsStream("/json/" + jsonName);
        try {
            return IOUtils.toString(resource, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
