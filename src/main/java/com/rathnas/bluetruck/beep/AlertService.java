package com.rathnas.bluetruck.beep;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

@Service
@Profile("${com.rathnas.bluetruck.beep.profiles:localhost}")
public class AlertService {

    @Value("${com.rathnas.bluetruck.beep.frequency:200}")
    private int frequency;

    @Value("${com.rathnas.bluetruck.beep.duration:200}")
    private int duration;

    @PostConstruct
    public void alert() {
        try {
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
            System.out.println("Alerted");
        } catch (LineUnavailableException e) {
            System.err.println("Line unavailable: " + e.getMessage());
        }
    }
}
