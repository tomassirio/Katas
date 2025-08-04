package com.tomassirio.practice.model;

public class DimmableLight implements Light {

    private static final Integer MIN_BRIGHTNESS = 0;
    private static final Integer MAX_BRIGHTNESS = 2;

    private Integer brightness;

    public DimmableLight() {
        this.brightness = MIN_BRIGHTNESS;
    }

    @Override
    public void turnOn() {
        this.brightness = Math.min(MAX_BRIGHTNESS, brightness + 1);
    }

    @Override
    public void turnOff() {
        this.brightness = Math.max(MIN_BRIGHTNESS, brightness - 1);
    }

    @Override
    public void toggle() {
        this.brightness = MAX_BRIGHTNESS;
    }

    @Override
    public boolean isOn() {
        return brightness > 0;
    }

    @Override
    public int getBrightness() {
        return brightness;
    }
}
