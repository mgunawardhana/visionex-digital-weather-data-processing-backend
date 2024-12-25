package com.visionex.backend.controller;

import com.visionex.backend.model.APIResponse;
import com.visionex.backend.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestController {
    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getWeatherSummary_ReturnsWeatherData_ForValidCity() {
        try {
            String city = "London";
            APIResponse apiResponse = new APIResponse();
            ResponseEntity<APIResponse> expectedResponse = ResponseEntity.ok(apiResponse);
            when(weatherService.getWeatherByCity(city)).thenReturn(expectedResponse);
            ResponseEntity<APIResponse> response = weatherController.getWeatherSummary(city);
            assertEquals(expectedResponse, response);
        } catch (IllegalArgumentException e) {
            assertEquals("City name cannot be empty", e.getMessage());
        }
    }

    @Test
    void getWeatherSummary_ReturnsError_ForInvalidCity() {
        try {
            String city = "InvalidCity";
            APIResponse apiResponse = new APIResponse();
            ResponseEntity<APIResponse> expectedResponse = ResponseEntity.status(404).body(apiResponse);
            when(weatherService.getWeatherByCity(city)).thenReturn(expectedResponse);
            ResponseEntity<APIResponse> response = weatherController.getWeatherSummary(city);
            assertEquals(expectedResponse, response);
        } catch (IllegalArgumentException e) {
            assertEquals("City name cannot be empty", e.getMessage());
        }
    }

    @Test
    void getWeatherSummary_ReturnsError_ForNullCity() {
        try {
            String city = null;
            APIResponse apiResponse = new APIResponse();
            ResponseEntity<APIResponse> expectedResponse = ResponseEntity.status(400).body(apiResponse);
            when(weatherService.getWeatherByCity(city)).thenReturn(expectedResponse);
            ResponseEntity<APIResponse> response = weatherController.getWeatherSummary(city);
            assertEquals(expectedResponse, response);
        } catch (IllegalArgumentException e) {
            assertEquals("City name cannot be empty", e.getMessage());
        }
    }


}
