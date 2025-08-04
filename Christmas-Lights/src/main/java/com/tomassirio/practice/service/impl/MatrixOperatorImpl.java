package com.tomassirio.practice.service.impl;

import com.tomassirio.practice.model.LightMatrix;
import com.tomassirio.practice.service.MatrixOperator;

import java.util.Arrays;

public class MatrixOperatorImpl implements MatrixOperator {
    @Override
    public Integer countTurnedOn(LightMatrix lightMatrix) {
        return Math.toIntExact(Arrays.stream(lightMatrix.getMatrix())
                .flatMap(Arrays::stream)
                .filter(Boolean.TRUE::equals)
                .count());
    }
}
