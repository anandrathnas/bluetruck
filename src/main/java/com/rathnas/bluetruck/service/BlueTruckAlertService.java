package com.rathnas.bluetruck.service;

import com.rathnas.bluetruck.config.BlueTruckConfigProperties;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

@Slf4j
@Service
public class BlueTruckAlertService {
    private final BlueTruckConfigProperties bluetruckConfigProperties;
    private final Environment env;
    private final boolean matchesProfiles;

    @Autowired
    public BlueTruckAlertService(BlueTruckConfigProperties bluetruckConfigProperties, Environment env) {
        this.bluetruckConfigProperties = bluetruckConfigProperties;
        this.env = env;
        matchesProfiles = env.matchesProfiles(bluetruckConfigProperties.getProfiles());
        log.debug("BlueTruckAlertService created: {}", bluetruckConfigProperties);
    }

    @PostConstruct
    public void alert() {
        try {
            if (!matchesProfiles) {
                log.debug("Beeping skipped since profiles don't match {} != {}"
                        , env.getActiveProfiles(), bluetruckConfigProperties.getProfiles());
                return;
            }
            log.debug("Going to beep");
            int duration = bluetruckConfigProperties.getDuration();
            int frequency = bluetruckConfigProperties.getFrequency();
            SourceDataLine line = AudioSystem.getSourceDataLine(new AudioFormat(44100, 16, 1, true, true));
            line.open();
            line.start();
            byte[] toneBuffer = new byte[duration * 44100 / 1000];
            for (int i = 0; i < toneBuffer.length; i++) {
                double angle = 2.0 * Math.PI * i / ((float) 44100 / frequency);
                toneBuffer[i] = (byte) (Math.sin(angle) * 127);
            }
            line.write(toneBuffer, 0, toneBuffer.length);
            line.drain();
            line.stop();
            line.close();
            log.debug("Done with beep");
        } catch (LineUnavailableException e) {
            log.warn("Problem beeping: {}", e.getMessage());
        }
    }
}
