package com.tomassirio.practice.model.user;

import com.tomassirio.practice.model.testDefinitions.CategoryResult;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserAppropriatenessTestTest {

    @Test
    void testConstructorAndGetters() {
        UUID userId = UUID.randomUUID();
        SecuritySegment securitySegment = SecuritySegment.CRYPTO_ETP;
        TestFlowState flowState = TestFlowState.IN_PROGRESS;
        Map<String, CategoryStatus> categoryStatuses = new HashMap<>();
        categoryStatuses.put("cat1", new CategoryStatus(new CategoryStatusId(UUID.randomUUID(), "cat1"), CategoryResult.PASSED, new HashMap<>()));

        UserAppropriatenessTest userTest = new UserAppropriatenessTest();
        userTest.setUserId(userId);
        userTest.setSecuritySegment(securitySegment);
        userTest.setFlowState(flowState);
        userTest.setCategoryStatuses(categoryStatuses);

        assertThat(userTest.getUserId()).isEqualTo(userId);
        assertThat(userTest.getSecuritySegment()).isEqualTo(securitySegment);
        assertThat(userTest.getFlowState()).isEqualTo(flowState);
        assertThat(userTest.getCategoryStatuses()).isEqualTo(categoryStatuses);
    }

    @Test
    void testSetters() {
        UserAppropriatenessTest userTest = new UserAppropriatenessTest();

        UUID userId = UUID.randomUUID();
        SecuritySegment securitySegment = SecuritySegment.COMPLEX_ETC;
        TestFlowState flowState = TestFlowState.COMPLETED_FAILED;
        Map<String, CategoryStatus> categoryStatuses = new HashMap<>();
        categoryStatuses.put("cat2", new CategoryStatus(new CategoryStatusId(UUID.randomUUID(), "cat2"), CategoryResult.FAILED, new HashMap<>()));

        userTest.setUserId(userId);
        userTest.setSecuritySegment(securitySegment);
        userTest.setFlowState(flowState);
        userTest.setCategoryStatuses(categoryStatuses);

        assertThat(userTest.getUserId()).isEqualTo(userId);
        assertThat(userTest.getSecuritySegment()).isEqualTo(securitySegment);
        assertThat(userTest.getFlowState()).isEqualTo(flowState);
        assertThat(userTest.getCategoryStatuses()).isEqualTo(categoryStatuses);
    }

    @Test
    void testNoArgsConstructor() {
        UserAppropriatenessTest userTest = new UserAppropriatenessTest();
        assertThat(userTest).isNotNull();
    }
}
