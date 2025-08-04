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
        // Ensure coordinates are within bounds
        int fromX = Math.max(from.x(), 0);
        int toX = Math.min(to.x(), lightMatrix.getMatrix().length - 1);

        int fromY = Math.max(from.y(), 0);
        int toY = Math.min(to.y(), lightMatrix.getMatrix()[0].length - 1);


        for (int i = fromX; i <= toX; i++) {
            for (int j = fromY; j <= toY; j++) {
                lightMatrix.getMatrix()[i][j] = Boolean.TRUE;
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
