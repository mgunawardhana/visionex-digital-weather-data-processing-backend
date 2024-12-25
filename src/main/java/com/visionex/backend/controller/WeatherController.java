package com.visionex.backend.controller;

import com.visionex.backend.model.APIResponse;
import com.visionex.backend.service.WeatherService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    @NonNull
    private final WeatherService weatherService;

    @GetMapping("/summary/{city}")
    public ResponseEntity<APIResponse> getWeatherSummary(@PathVariable String city) {
        log.info("Fetching weather data for city: {}", city);
        var response = weatherService.getWeatherByCity(city);
        log.info("Weather data fetched successfully for city: {}", city);
        return response;
    }
}

