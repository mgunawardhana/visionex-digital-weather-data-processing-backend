package com.visionex.backend.service;

import org.springframework.http.ResponseEntity;

public interface WeatherService {

    ResponseEntity<?> getWeatherByCity(String city);
}
