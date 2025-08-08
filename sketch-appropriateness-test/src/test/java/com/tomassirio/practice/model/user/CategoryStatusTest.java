package com.tomassirio.practice.model.user;

import com.tomassirio.practice.model.testDefinitions.CategoryResult;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryStatusTest {

    @Test
    void testConstructorAndGetters() {
        UUID userTestId = UUID.randomUUID();
        String categoryId = "testCategory";
        CategoryStatusId id = new CategoryStatusId(userTestId, categoryId);
        CategoryResult result = CategoryResult.PASSED;
        Map<String, String> userAnswers = new HashMap<>();
        userAnswers.put("q1", "a1");

        CategoryStatus categoryStatus = new CategoryStatus(id, result, userAnswers);

        assertThat(categoryStatus.getId()).isEqualTo(id);
        assertThat(categoryStatus.getResult()).isEqualTo(result);
        assertThat(categoryStatus.getUserAnswers()).isEqualTo(userAnswers);
    }

    @Test
    void testSetters() {
        CategoryStatus categoryStatus = new CategoryStatus();

        UUID userTestId = UUID.randomUUID();
        String categoryId = "newCategory";
        CategoryStatusId id = new CategoryStatusId(userTestId, categoryId);
        CategoryResult result = CategoryResult.FAILED;
        Map<String, String> userAnswers = new HashMap<>();
        userAnswers.put("q2", "a2");

        categoryStatus.setId(id);
        categoryStatus.setResult(result);
        categoryStatus.setUserAnswers(userAnswers);

        assertThat(categoryStatus.getId()).isEqualTo(id);
        assertThat(categoryStatus.getResult()).isEqualTo(result);
        assertThat(categoryStatus.getUserAnswers()).isEqualTo(userAnswers);
    }

    @Test
    void testNoArgsConstructor() {
        CategoryStatus categoryStatus = new CategoryStatus();
        assertThat(categoryStatus).isNotNull();
    }
}
