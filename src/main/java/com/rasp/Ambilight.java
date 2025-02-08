package com.rasp;

import com.pi4j.context.Context;
import com.pi4j.Pi4J;
import com.pi4j.io.spi.Spi;
import com.pi4j.io.spi.SpiBus;
import com.pi4j.io.spi.SpiMode;

public class Ambilight {
    public static void main(String[] args) {
        Context pi4j = Pi4J.newAutoContext();

        Spi spi = pi4j.create(Spi.newConfigBuilder(pi4j)
                .id("SPI0")
                .bus(SpiBus.BUS_0)
                .mode(SpiMode.MODE_0)
                .baud(3200000) // Adjust based on your strip
                .build());

        byte[] ledData = new byte[30 * 3]; // 30 LEDs, 3 bytes each (RGB)

        for (int i = 0; i < ledData.length; i += 3) {
            ledData[i] = (byte) 255;   // Red
            ledData[i + 1] = (byte) 0; // Green
            ledData[i + 2] = (byte) 0; // Blue
        }

        spi.write(ledData);
        pi4j.shutdown();
    }
}
