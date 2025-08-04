package com.tomassirio.practice.service;

import com.tomassirio.practice.model.Coordinate;
import com.tomassirio.practice.model.DimmableLight;
import com.tomassirio.practice.model.LightMatrix;
import com.tomassirio.practice.service.impl.LightServiceImpl;
import com.tomassirio.practice.service.impl.MatrixOperatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IntegrationTest {

    private final LightService lightService = new LightServiceImpl();
    private final MatrixOperator matrixOperator = new MatrixOperatorImpl();

    @MethodSource("turnOnInstructions")
    @ParameterizedTest
    public void testTurnOnInstructions(Coordinate from, Coordinate to, Integer expectedResult) {
        // Given
        LightMatrix lightMatrix = new LightMatrix(1_000, 1_000);

        // When
        lightService.turnOn(from, to, lightMatrix);
        Integer turnedOn = matrixOperator.calculateTotalBrightness(lightMatrix);

        // Then
        assertThat(turnedOn).isEqualTo(expectedResult);
    }

    @MethodSource("turnOffInstructions")
    @ParameterizedTest
    public void testTurnOffInstructions(Coordinate from, Coordinate to, Integer expectedResult) {
        // Given
        LightMatrix lightMatrix = new LightMatrix(1_000, 1_000);
        lightService.turnOn(new Coordinate(0,0), new Coordinate(1_000, 1_000), lightMatrix);

        // When
        lightService.turnOff(from, to, lightMatrix);
        Integer turnedOff = matrixOperator.countLights(lightMatrix, Boolean.FALSE);

        // Then
        assertThat(turnedOff).isEqualTo(expectedResult);
    }

    @MethodSource("toggleInstructions")
    @ParameterizedTest
    public void testToggleInstructions(Coordinate from, Coordinate to, Integer expectedResult) {
        // Given
        LightMatrix lightMatrix = new LightMatrix(1_000, 1_000);

        // When
        lightService.toggle(from, to, lightMatrix);
        Integer turnedOn = matrixOperator.calculateTotalBrightness(lightMatrix);

        // Then
        assertThat(turnedOn).isEqualTo(expectedResult);
    }

    @Test
    public void testAllInstructionsInOrderResultsIn230022TurnedOnLights() {
        // Given
        LightMatrix lightMatrix = new LightMatrix(1_000, 1_000);

        // When
        lightService.turnOn(new Coordinate(887, 9), new Coordinate(959, 629), lightMatrix);
        lightService.turnOn(new Coordinate(454, 398), new Coordinate(844, 448), lightMatrix);
        lightService.turnOff(new Coordinate(539, 243), new Coordinate(559, 965), lightMatrix);
        lightService.turnOff(new Coordinate(370, 819), new Coordinate(676, 868), lightMatrix);
        lightService.turnOff(new Coordinate(145, 40), new Coordinate(370, 997), lightMatrix);
        lightService.turnOff(new Coordinate(301, 3), new Coordinate(808, 453), lightMatrix);
        lightService.turnOn(new Coordinate(351, 678), new Coordinate(951, 908), lightMatrix);
        lightService.toggle(new Coordinate(720, 196), new Coordinate(897, 994), lightMatrix);
        lightService.toggle(new Coordinate(831, 394), new Coordinate(904, 860), lightMatrix);

        Integer turnedOn = matrixOperator.calculateTotalBrightness(lightMatrix);
        assertThat(turnedOn).isEqualTo(230022);
    }

    @Test
    public void testTotalBrightnessForDimmableMatrix() {
        LightMatrix lightMatrix = new LightMatrix(1_000, 1_000, DimmableLight::new);

        lightService.toggle(new Coordinate(0, 0), new Coordinate(999, 999), lightMatrix);

        Integer totalBrightness = matrixOperator.calculateTotalBrightness(lightMatrix);
        assertThat(totalBrightness).isEqualTo(2_000_000);
    }

    public static Stream<Arguments> turnOnInstructions() {
        return Stream.of(
                Arguments.of(new Coordinate(887, 9), new Coordinate(959, 629), 45_333),
                Arguments.of(new Coordinate(454, 398), new Coordinate(844, 448), 19_941),
                Arguments.of(new Coordinate(351, 678), new Coordinate(951, 908), 138_831)
        );
    }

    public static Stream<Arguments> turnOffInstructions() {
        return Stream.of(
                Arguments.of(new Coordinate(539, 243), new Coordinate(559, 965), 15_183),
                Arguments.of(new Coordinate(370, 819), new Coordinate(676, 868), 15_350),
                Arguments.of(new Coordinate(145, 40), new Coordinate(370, 997), 216_508),
                Arguments.of(new Coordinate(301, 3), new Coordinate(808, 453), 229_108)
        );
    }

    public static Stream<Arguments> toggleInstructions() {
        return Stream.of(
                Arguments.of(new Coordinate(720, 196), new Coordinate(897, 994), 142_222),
                Arguments.of(new Coordinate(831, 394), new Coordinate(904, 860), 34_558)
        );
    }
}
