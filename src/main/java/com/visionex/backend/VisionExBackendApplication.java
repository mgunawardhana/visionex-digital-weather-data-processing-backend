package com.visionex.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@SpringBootApplication
@EnableAsync
public class VisionExBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisionExBackendApplication.class, args);
	}

}
