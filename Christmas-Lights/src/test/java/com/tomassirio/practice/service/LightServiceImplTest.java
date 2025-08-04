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
        assertThat(lightMatrix.getMatrix()[0][0].isOn()).isTrue();

        Arrays.stream(lightMatrix.getMatrix()).forEach(
            row -> Arrays.stream(row).forEach(light ->
                assertThat(light.isOn()).isTrue())
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
        assertThat(lightMatrix.getMatrix()[0][0].isOn()).isTrue();
        assertThat(lightMatrix.getMatrix()[4][4].isOn()).isTrue();

        Arrays.stream(lightMatrix.getMatrix()).forEach(
            row -> Arrays.stream(row).forEach(light ->
                assertThat(light.isOn()).isTrue())
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
            row -> Arrays.stream(row).forEach(light ->
                assertThat(light.isOn()).isFalse())
        );
    }

    @Test
    public void test_toggle() {
        // Given
        LightMatrix lightMatrix = new LightMatrix(5, 5);
        Coordinate from = new Coordinate(0, 0);
        Coordinate to = new Coordinate(2, 2);
        lightService.turnOn(from, to, lightMatrix);

        // When
        Coordinate toToggle = new Coordinate(5, 5);
        lightService.toggle(from, toToggle, lightMatrix);

        // Then
        for (int i = from.x(); i <= to.x(); i ++) {
            for (int j = from.y(); j <= to.y(); j ++) {
                assertThat(lightMatrix.getMatrix()[i][j].isOn()).isFalse();
            }
        }

        for (int i = to.x() + 1; i < lightMatrix.getMatrix().length; i++) {
            for (int j = to.y() + 1; j < lightMatrix.getMatrix()[0].length; j++) {
                assertThat(lightMatrix.getMatrix()[i][j].isOn()).isTrue();
            }
        }
    }
}
