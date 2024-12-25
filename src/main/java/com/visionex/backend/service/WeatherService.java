package com.visionex.backend.service;

import com.visionex.backend.model.APIResponse;
import org.springframework.http.ResponseEntity;

public interface WeatherService {

    /**
     * Fetches weather data for a given city.
     *
     * @param city the name of the city to fetch weather data for
     * @return a ResponseEntity containing the weather data or an error message
     */
    ResponseEntity<APIResponse> getWeatherByCity(String city);
}
