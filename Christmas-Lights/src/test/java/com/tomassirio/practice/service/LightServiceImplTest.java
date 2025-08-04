package com.tomassirio.practice.service;

import com.tomassirio.practice.model.Coordinate;
import com.tomassirio.practice.model.LightMatrix;
import com.tomassirio.practice.service.impl.LightServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LightServiceImplTest {

    private final LightService lightService = new LightServiceImpl();

    @Test
    public void test_turnOn() {
        // Given
        LightMatrix lightMatrix = new LightMatrix(5, 5);
        Coordinate from = new Coordinate(0, 0);
        Coordinate to = new Coordinate(4, 4);

        // When
        lightService.turnOn(from, to, lightMatrix);

        // Then
        assertThat(lightMatrix.getMatrix()[0][0]).isTrue();

        Arrays.stream(lightMatrix.getMatrix()).forEach(
            row -> Arrays.stream(row).forEach(light -> assertThat(light).isTrue())
        );
    }

    @Test
    public void test_turnOn_outOfBounds_wrapsWithoutException() {
        // Given
        LightMatrix lightMatrix = new LightMatrix(5, 5);
        Coordinate from = new Coordinate(-1, -1);
        Coordinate to = new Coordinate(6, 6);

        // When
        lightService.turnOn(from, to, lightMatrix);

        // Then
        assertThat(lightMatrix.getMatrix()[0][0]).isTrue();
        assertThat(lightMatrix.getMatrix()[4][4]).isTrue();

        Arrays.stream(lightMatrix.getMatrix()).forEach(
            row -> Arrays.stream(row).forEach(light -> assertThat(light).isTrue())
        );
    }

    @Test
    public void test_turnOff() {
        // Given
        LightMatrix lightMatrix = new LightMatrix(5, 5);
        Coordinate from = new Coordinate(0, 0);
        Coordinate to = new Coordinate(4, 4);
        lightService.turnOn(from, to, lightMatrix);

        // When
        lightService.turnOff(from, to, lightMatrix);

        // Then
        Arrays.stream(lightMatrix.getMatrix()).forEach(
            row -> Arrays.stream(row).forEach(light -> assertThat(light).isFalse())
        );
    }
}
