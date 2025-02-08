package com.rasp;

import com.pi4j.context.Context;
import com.pi4j.Pi4J;
import com.pi4j.io.spi.Spi;
import com.pi4j.io.spi.SpiBus;
import com.pi4j.io.spi.SpiMode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ambilight implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Ambilight.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Your logic for controlling WS2812 LEDs goes here
        System.out.println("Starting WS2812 LED control...");

        // Initialize and control your WS2812 strip here
        // This part will be similar to the code you already wrote for controlling the LEDs
        controlLEDs();
    }

    private void controlLEDs() {
        Context pi4j = Pi4J.newAutoContext();

        Spi spi = pi4j.create(Spi.newConfigBuilder(pi4j)
                .id("SPI0")
                .bus(SpiBus.BUS_0)
                .mode(SpiMode.MODE_0)
                .baud(3200000) // Adjust based on your strip
                .address(0x00)
                .build());

        byte[] ledData = new byte[30 * 3]; // 30 LEDs, 3 bytes each (RGB)

        for (int i = 0; i < ledData.length; i += 3) {
            ledData[i] = (byte) 255;   // Red
            ledData[i + 1] = (byte) 0; // Green
            ledData[i + 2] = (byte) 0; // Blue
        }

        spi.write(ledData);
        pi4j.shutdown();

        System.out.println("All LEDs are now on!");
    }
}