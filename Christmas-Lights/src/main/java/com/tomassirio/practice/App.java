package com.tomassirio.practice;

import com.tomassirio.practice.model.Coordinate;
import com.tomassirio.practice.model.DimmableLight;
import com.tomassirio.practice.model.LightMatrix;
import com.tomassirio.practice.service.LightService;
import com.tomassirio.practice.service.MatrixOperator;
import com.tomassirio.practice.service.impl.LightServiceImpl;
import com.tomassirio.practice.service.impl.MatrixOperatorImpl;

/*
 * https://kata-log.rocks/christmas-lights-kata
 */
public class App {
    public static void main( String[] args ) {
        LightService lightService = new LightServiceImpl();
        MatrixOperator matrixOperator = new MatrixOperatorImpl();
        final Integer MATRIX_ROW_SIZE = 1_000;

        // Part 1
        LightMatrix lightMatrix = new LightMatrix(MATRIX_ROW_SIZE, MATRIX_ROW_SIZE);

        lightService.turnOn(new Coordinate(887, 9), new Coordinate(959, 629), lightMatrix);
        lightService.turnOn(new Coordinate(454, 398), new Coordinate(844, 448), lightMatrix);
        lightService.turnOff(new Coordinate(539, 243), new Coordinate(559, 965), lightMatrix);
        lightService.turnOff(new Coordinate(370, 819), new Coordinate(676, 868), lightMatrix);
        lightService.turnOff(new Coordinate(145, 40), new Coordinate(370, 997), lightMatrix);
        lightService.turnOff(new Coordinate(301, 3), new Coordinate(808, 453), lightMatrix);
        lightService.turnOn(new Coordinate(351, 678), new Coordinate(951, 908), lightMatrix);
        lightService.toggle(new Coordinate(720, 196), new Coordinate(897, 994), lightMatrix);
        lightService.toggle(new Coordinate(831, 394), new Coordinate(904, 860), lightMatrix);

        Integer turnedOnLights = matrixOperator.calculateTotalBrightness(lightMatrix);
        System.out.println("Number of lights turned on: " + turnedOnLights);

        // Part 2
        LightMatrix dimmableLightMatrix = new LightMatrix(MATRIX_ROW_SIZE, MATRIX_ROW_SIZE, DimmableLight::new);
        lightService.toggle(new Coordinate(0, 0), new Coordinate(999, 999), dimmableLightMatrix);

        Integer totalBrightness = matrixOperator.calculateTotalBrightness(dimmableLightMatrix);
        System.out.println("Total Brightness for Matrix: " + totalBrightness);
    }
}
