package com.rathnas.bluetruck.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.rathnas.bluetruck")
public class BlueTruckConfigProperties {
    private String[] profiles = {"dev", "default", "local", "localhost"};
    private int frequency = 200;
    private int duration = 200;
}