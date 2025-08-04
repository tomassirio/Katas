package com.tomassirio.practice.model;

public class BasicLight implements Light {

    private Boolean state;

    public BasicLight() {
        this.state = false;
    }

    @Override
    public void turnOn() {
        this.state = Boolean.TRUE;
    }

    @Override
    public void turnOff() {
        this.state = Boolean.FALSE;
    }

    @Override
    public void toggle() {
        this.state = !this.state;
    }

    @Override
    public boolean isOn() {
        return state;
    }
}
