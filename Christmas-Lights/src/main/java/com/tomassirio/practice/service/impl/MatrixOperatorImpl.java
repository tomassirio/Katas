package com.tomassirio.practice.service.impl;

import com.tomassirio.practice.model.LightMatrix;
import com.tomassirio.practice.service.MatrixOperator;

import java.util.Arrays;

public class MatrixOperatorImpl implements MatrixOperator {
    @Override
    public Integer countLights(LightMatrix lightMatrix, Boolean turnedOn) {
        return Math.toIntExact(Arrays.stream(lightMatrix.getMatrix())
                .flatMap(Arrays::stream)
                .filter(turnedOn::equals)
                .count());
    }
}
