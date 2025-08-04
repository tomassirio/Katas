package com.tomassirio.practice.service;

import com.tomassirio.practice.model.Coordinate;
import com.tomassirio.practice.model.LightMatrix;

public interface LightService {

    void turnOn(Coordinate from, Coordinate to, LightMatrix lightMatrix);

    void turnOff(Coordinate from, Coordinate to, LightMatrix lightMatrix);

    void toggle(Coordinate from, Coordinate to, LightMatrix lightMatrix);
}
