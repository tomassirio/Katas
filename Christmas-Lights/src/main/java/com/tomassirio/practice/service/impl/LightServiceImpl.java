package com.tomassirio.practice.service.impl;

import com.tomassirio.practice.model.Coordinate;
import com.tomassirio.practice.model.LightMatrix;
import com.tomassirio.practice.service.LightService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LightServiceImpl implements LightService {
    @Override
    public void turnOn(Coordinate from, Coordinate to, LightMatrix lightMatrix) {
        for (int i = from.x(); i <= to.x(); i++) {
            for (int j = from.y(); j <= to.y(); j++) {
                lightMatrix.getMatrix()[i][j] = true;
            }
        }
    }

    @Override
    public void turnOff(Coordinate from, Coordinate to, LightMatrix lightMatrix) {

    }

    @Override
    public void toggle(Coordinate from, Coordinate to, LightMatrix lightMatrix) {

    }
}
