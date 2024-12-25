package com.visionex.backend.service;

import com.visionex.backend.client.WeatherApiClient;
import com.visionex.backend.model.APIResponse;
import com.visionex.backend.model.weatherdata.WeatherEntry;
import com.visionex.backend.service.impl.WeatherServiceImpl;
import com.visionex.backend.util.ResponseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WeatherServiceImplTest {


    @Mock
    private WeatherApiClient weatherApiClient;

    @Mock
    private ResponseUtil responseUtil;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getWeatherByCity_ReturnsWeatherData_ForValidCity() {
        try {
            String city = "London";
            WeatherEntry weatherEntry = new WeatherEntry();
            ResponseEntity<APIResponse> expectedResponse = ResponseEntity.ok(APIResponse.builder().statusCode("200").statusMessage("Success").build());
            when(weatherApiClient.fetchWeatherDetailsByLocationName(city)).thenReturn(CompletableFuture.completedFuture(weatherEntry));
            when(responseUtil.wrapSuccess(weatherEntry, HttpStatus.OK)).thenReturn(expectedResponse);
            ResponseEntity<APIResponse> response = weatherService.getWeatherByCity(city);
            assertEquals(expectedResponse, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getWeatherByCity_ThrowsException_ForNullCity() {
        try {
            String city = null;
            IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
                weatherService.getWeatherByCity(city);
            });
            assertEquals("City name cannot be empty", exception.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getWeatherByCity_ReturnsError_ForApiException() {
        try {
            String city = "London";
            ResponseEntity<APIResponse> expectedResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(APIResponse.builder().statusCode("500")
                            .statusMessage("Error occurred while fetching weather data")
                            .errorType("API error").build());
            when(weatherApiClient.fetchWeatherDetailsByLocationName(city)).thenThrow(new RuntimeException("API error"));
            when(responseUtil.wrapError("Error occurred while fetching weather data", "API error",
                    HttpStatus.INTERNAL_SERVER_ERROR)).thenReturn(expectedResponse);
            ResponseEntity<APIResponse> response = weatherService.getWeatherByCity(city);
            assertEquals(expectedResponse, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
