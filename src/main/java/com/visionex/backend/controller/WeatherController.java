package com.visionex.backend.controller;

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
    public ResponseEntity<?> getInstructor(@PathVariable String city) {
        System.out.println("City: " + city);
        return weatherService.getWeatherByCity(city);
    }

}
