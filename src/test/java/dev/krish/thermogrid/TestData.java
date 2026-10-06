package dev.krish.thermogrid;

import dev.krish.thermogrid.config.GridStressProperties;
import dev.krish.thermogrid.entity.HeatwaveEvent;
import dev.krish.thermogrid.entity.Region;

import java.util.concurrent.atomic.AtomicLong;

/** Builders for test fixtures, so tests read as data rather than constructor noise. */
public final class TestData {

    private static final AtomicLong IDS = new AtomicLong();

    private TestData() {
    }

    /** The same constants as application.properties, built by hand. */
    public static GridStressProperties defaultProperties() {
        return new GridStressProperties(30, 1.0, 0.03, 0.01, 0.5);
    }

    public static Region region(String name, int population, double baseCapacityMw) {
        return new Region(IDS.incrementAndGet(), name, population, baseCapacityMw);
    }

    public static HeatwaveEvent event(Region region, int targetYear, double peakHeatIndex) {
        return new HeatwaveEvent(IDS.incrementAndGet(), region, targetYear, peakHeatIndex, true);
    }
}
