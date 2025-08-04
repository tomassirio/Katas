package com.tomassirio.practice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;

@Setter
@Getter
public class LightMatrix {

    private Light[][] matrix;

    public LightMatrix(Integer rows, Integer cols) {
        this(rows, cols, BasicLight::new);
    }

    public LightMatrix(Integer rows, Integer cols, Supplier<Light> lightSupplier) {
        this.matrix = new Light[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = lightSupplier.get();
            }
        }
    }
}
