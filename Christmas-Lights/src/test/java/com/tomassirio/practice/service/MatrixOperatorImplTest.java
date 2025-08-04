package com.tomassirio.practice.service;

import com.tomassirio.practice.model.LightMatrix;
import com.tomassirio.practice.service.impl.MatrixOperatorImpl;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MatrixOperatorImplTest {

    private final MatrixOperator matrixOperator = new MatrixOperatorImpl();

    @Test
    public void testCountTurnedOnLights() {
        // Given
        LightMatrix lightMatrix = new LightMatrix(5, 5);

        Boolean[][] lights = new Boolean[5][5];
        IntStream.range(0, 5).forEach(i -> lights[i][i] = Boolean.TRUE);

        lightMatrix.setMatrix(lights);

        // When
        Integer turnedOn = matrixOperator.countLights(lightMatrix, Boolean.TRUE);

        assertThat(turnedOn).isEqualTo(5);
    }
}
