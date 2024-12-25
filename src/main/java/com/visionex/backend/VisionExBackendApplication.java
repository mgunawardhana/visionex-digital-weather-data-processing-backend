package com.visionex.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class VisionExBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(VisionExBackendApplication.class, args);
		log.info("Starting VisionExBackendApplication ********");
	}

}
