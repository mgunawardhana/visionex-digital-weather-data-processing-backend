package com.visionex.backend.model.weatherdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;
}
