package com.visionex.backend.service;

import com.visionex.backend.client.WeatherApiClient;
import com.visionex.backend.model.WeatherData;
import com.visionex.backend.model.WeatherEntry;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @NonNull
    private final WeatherApiClient weatherApiClient;

    public WeatherServiceImpl(@NonNull WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    @Override
    public ResponseEntity<WeatherEntry> getWeatherByCity(String city) {
        WeatherEntry weatherData = weatherApiClient.fetchWeatherDetailsByLocationName(city);
        return ResponseEntity.ok(weatherData);
    }
}
