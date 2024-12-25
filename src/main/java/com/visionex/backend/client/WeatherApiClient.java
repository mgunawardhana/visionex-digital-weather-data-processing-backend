package com.visionex.backend.client;

import com.visionex.backend.model.WeatherData;
import com.visionex.backend.model.WeatherEntry;
import com.visionex.backend.util.Constant;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class WeatherApiClient {

    @Value("${API_KEY}")
    private String apiKey;

    WebClient.Builder builder = WebClient.builder();

    public WeatherEntry fetchWeatherDetailsByLocationName(String locationName) {
        WeatherEntry block = builder.build().get().uri(Constant.BASE_URL).retrieve().bodyToMono(WeatherEntry.class).block();
        System.out.println("Weather Data: " + block);
        return block;
    }
}
