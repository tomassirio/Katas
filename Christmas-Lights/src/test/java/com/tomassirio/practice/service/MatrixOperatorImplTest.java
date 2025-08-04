package com.tomassirio.practice.service;

import com.tomassirio.practice.model.BasicLight;
import com.tomassirio.practice.model.DimmableLight;
import com.tomassirio.practice.model.Light;
import com.tomassirio.practice.model.LightMatrix;
import com.tomassirio.practice.service.impl.MatrixOperatorImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class MatrixOperatorImplTest {

    private final MatrixOperator matrixOperator = new MatrixOperatorImpl();

    @Test
    public void testCountTurnedOnLights() {
        // Given
        LightMatrix lightMatrix = new LightMatrix(5, 5);

        Light[][] lights = new BasicLight[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                lights[i][j] = new BasicLight();
            }
        }

        IntStream.range(0, 5).forEach(i -> lights[i][i].turnOn());

        lightMatrix.setMatrix(lights);

        // When
        Integer turnedOn = matrixOperator.countLights(lightMatrix, Boolean.TRUE);

        assertThat(turnedOn).isEqualTo(5);
    }

    @Test
    public void testCalculateTotalBrightness() {
        // Given
        LightMatrix lightMatrix = new LightMatrix(1_000, 1_000, DimmableLight::new);

        Arrays.stream(lightMatrix.getMatrix())
                .forEach(lightRow -> Arrays.stream(lightRow)
                        .forEach(Light::toggle));

        // When
        Integer totalBrightness = matrixOperator.calculateTotalBrightness(lightMatrix);
        assertThat(totalBrightness).isEqualTo(2_000_000);
    }
}
