package com.visionex.backend.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

    public static final String BASE_URL = "https://api.openweathermap.org";
    public static final String END_POINT = "/data/2.5/weather";
    public static final String ERROR = "error";
}
