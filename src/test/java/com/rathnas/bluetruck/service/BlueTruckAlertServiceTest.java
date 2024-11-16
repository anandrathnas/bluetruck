package com.rathnas.bluetruck.service;

import com.rathnas.bluetruck.config.BlueTruckConfigProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import javax.sound.sampled.LineUnavailableException;
import static org.mockito.Mockito.*;

class BlueTruckAlertServiceTest {

	@Mock
	private BlueTruckConfigProperties configProperties;

	@Mock
	private Environment environment;

	private BlueTruckAlertService blueTruckAlertService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		when(configProperties.getProfiles()).thenReturn(new String[]{"dev", "test"});
		when(configProperties.getFrequency()).thenReturn(200);
		when(configProperties.getDuration()).thenReturn(200);
		blueTruckAlertService = new BlueTruckAlertService(configProperties, environment);
	}

	@Test
	void testAlertWhenProfilesDoNotMatch() {
		when(environment.matchesProfiles(any(String[].class))).thenReturn(false);
		blueTruckAlertService.alert();
		verify(environment, times(1)).matchesProfiles(any(String[].class));
	}

	@Test
	void testAlertWhenProfilesMatch() throws LineUnavailableException {
		when(environment.matchesProfiles(any(String[].class))).thenReturn(true);
		blueTruckAlertService.alert();
		verify(environment).matchesProfiles(any(String[].class));
	}

	@Test
	void testAlertHandlesLineUnavailableException() throws LineUnavailableException {
		when(environment.matchesProfiles(any(String[].class))).thenReturn(true);
		mockStatic(javax.sound.sampled.AudioSystem.class)
				.when(() -> javax.sound.sampled.AudioSystem.getSourceDataLine(any()))
				.thenThrow(new LineUnavailableException("Simulated exception"));
		blueTruckAlertService.alert();
	}

}
