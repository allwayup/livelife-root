package com.allwayup.config;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class AutoConfigurationConfig {

    @Bean
    public PropertySourceLocator propertySourceLocator() {
        return environment -> {
            Map<String, Object> properties = new HashMap<>();
            PropertySource<Map<String, Object>> source = new MapPropertySource("properties", properties);
            return source;
        };
    }
}
