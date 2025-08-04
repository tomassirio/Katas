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
        Coordinate boundedFrom = boundFromCoordinate(from);
        Coordinate boundedTo = boundToCoordinate(to, lightMatrix.getMatrix().length);

        for (int i = boundedFrom.x(); i <= boundedTo.x(); i++) {
            for (int j = boundedFrom.y(); j <= boundedTo.y(); j++) {
                lightMatrix.getMatrix()[i][j] = Boolean.TRUE;
            }
        }
    }

    @Override
    public void turnOff(Coordinate from, Coordinate to, LightMatrix lightMatrix) {
        // Ensure coordinates are within bounds
        Coordinate boundedFrom = boundFromCoordinate(from);
        Coordinate boundedTo = boundToCoordinate(to, lightMatrix.getMatrix().length);

        for (int i = boundedFrom.x(); i <= boundedTo.x(); i++) {
            for (int j = boundedFrom.y(); j <= boundedTo.y(); j++) {
                lightMatrix.getMatrix()[i][j] = Boolean.FALSE;
            }
        }
    }

    @Override
    public void toggle(Coordinate from, Coordinate to, LightMatrix lightMatrix) {

    }

    private Coordinate boundFromCoordinate(Coordinate from) {
        int fromX = Math.max(from.x(), 0);
        int fromY = Math.max(from.y(), 0);
        return new Coordinate(fromX, fromY);
    }

    private Coordinate boundToCoordinate(Coordinate to, int matrixLength) {
        int toX = Math.min(to.x(), matrixLength - 1);
        int toY = Math.min(to.y(), matrixLength - 1);
        return new Coordinate(toX, toY);
    }
}
