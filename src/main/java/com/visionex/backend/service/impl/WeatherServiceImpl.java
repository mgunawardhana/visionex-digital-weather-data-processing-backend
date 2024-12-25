package com.visionex.backend.service.impl;

import com.visionex.backend.client.WeatherApiClient;
import com.visionex.backend.model.APIResponse;
import com.visionex.backend.model.weatherdata.WeatherEntry;
import com.visionex.backend.service.WeatherService;
import com.visionex.backend.util.ResponseUtil;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class WeatherServiceImpl implements WeatherService {

    @NonNull
    private final WeatherApiClient weatherApiClient;
    private final ResponseUtil responseUtil;

    public WeatherServiceImpl(@NonNull WeatherApiClient weatherApiClient, ResponseUtil responseUtil) {
        this.weatherApiClient = weatherApiClient;
        this.responseUtil = responseUtil;
    }

    @Override
    public ResponseEntity<APIResponse> getWeatherByCity(String city) {
       if(city == null || city.isEmpty()) {
            throw new IllegalArgumentException("City name cannot be empty");
        }

        try {
            CompletableFuture<WeatherEntry> weatherFuture = weatherApiClient.fetchWeatherDetailsByLocationName(city);
            WeatherEntry weatherEntry = weatherFuture.join();
            return responseUtil.wrapSuccess(weatherEntry, HttpStatus.OK);
        } catch (Exception e) {
            return responseUtil.wrapError("Error occurred while fetching weather data", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

