package com.tomassirio.practice.model.user;

public enum TestFlowState {
    NOT_STARTED,
    IN_PROGRESS, // User has started but not completed
    COMPLETED_PASSED,
    COMPLETED_WITH_WARNING,
    COMPLETED_FAILED,
}