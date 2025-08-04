package com.tomassirio.practice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public class LightMatrix {

    @Getter
    @Setter
    private Boolean[][] matrix;

    public LightMatrix(Integer rows, Integer cols) {
        this.matrix = new Boolean[rows][cols];
        Arrays.stream(this.matrix)
                .iterator()
                .forEachRemaining(light -> Arrays.fill(light, false));
    }

}

