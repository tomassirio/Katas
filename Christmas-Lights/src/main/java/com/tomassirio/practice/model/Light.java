package com.tomassirio.practice.model;

public interface Light {
    void turnOn();
    void turnOff();
    void toggle();
    boolean isOn();
    default int getBrightness() {
        return isOn() ? 1 : 0;
    }
}
