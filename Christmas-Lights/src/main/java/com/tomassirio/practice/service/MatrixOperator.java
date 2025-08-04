package com.tomassirio.practice.service;

import com.tomassirio.practice.model.LightMatrix;

public interface MatrixOperator {
    Integer countLights(LightMatrix lightMatrix, Boolean turnedOn);
}
