package com.visionex.backend.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;


@Configuration
@EnableCaching
@EnableAsync
public class CacheConfig {

    /**
     * Configures the cache manager for the application.
     *
     * @return a CacheManager instance configured with a cache named "weatherSummary"
     */
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("weatherSummary");
    }
}