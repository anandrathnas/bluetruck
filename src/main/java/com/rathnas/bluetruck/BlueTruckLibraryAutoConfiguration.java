package com.rathnas.bluetruck;

import com.rathnas.bluetruck.config.BlueTruckConfigProperties;
import com.rathnas.bluetruck.service.BlueTruckAlertService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@AutoConfiguration
@EnableConfigurationProperties(BlueTruckConfigProperties.class)
public class BlueTruckLibraryAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public BlueTruckAlertService bluetruckAlertService(BlueTruckConfigProperties bluetruckConfigProperties,
													   Environment env) {
		return new BlueTruckAlertService(bluetruckConfigProperties, env);
	}
}
