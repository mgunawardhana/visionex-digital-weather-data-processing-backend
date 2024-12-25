package com.visionex.backend.model.weatherdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sys {

        private int type;
        private int id;
        private double message;
        private String country;
        private long sunrise;
        private long sunset;
}
