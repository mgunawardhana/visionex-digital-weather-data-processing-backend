package com.visionex.backend.client;

import com.visionex.backend.model.weatherdata.WeatherEntry;
import com.visionex.backend.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class WeatherApiClient {

    @Value("${API_KEY}")
    private String apiKey;

    private final WebClient webClient;

    public WeatherApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Constant.BASE_URL).build();
    }

    @Async
    @Cacheable("weatherSummary")
    public CompletableFuture<WeatherEntry> fetchWeatherDetailsByLocationName(String locationName) {
        log.info("Fetching weather data asynchronously for location: {}", locationName);
        WeatherEntry weatherEntry = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(Constant.END_POINT)
                        .queryParam("q", locationName)
                        .queryParam("APPID", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(WeatherEntry.class)
                .block();

        log.info("Fetched weather data: {}", weatherEntry);
        return CompletableFuture.completedFuture(weatherEntry);
    }
}
