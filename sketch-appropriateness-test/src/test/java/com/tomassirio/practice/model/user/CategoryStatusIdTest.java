package com.tomassirio.practice.model.user;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryStatusIdTest {

    @Test
    void testConstructorAndGetters() {
        UUID userTestId = UUID.randomUUID();
        String categoryId = "testCategory";
        CategoryStatusId id = new CategoryStatusId(userTestId, categoryId);

        assertThat(id.getUserTestId()).isEqualTo(userTestId);
        assertThat(id.getCategoryId()).isEqualTo(categoryId);
    }

    @Test
    void testEqualsAndHashCode() {
        UUID userTestId1 = UUID.randomUUID();
        String categoryId1 = "testCategory1";
        CategoryStatusId id1 = new CategoryStatusId(userTestId1, categoryId1);

        CategoryStatusId id2 = new CategoryStatusId(userTestId1, categoryId1);

        UUID userTestId3 = UUID.randomUUID();
        String categoryId3 = "testCategory3";
        CategoryStatusId id3 = new CategoryStatusId(userTestId3, categoryId3);

        // Test equality
        assertThat(id1).isEqualTo(id2);
        assertThat(id1).hasSameHashCodeAs(id2);

        // Test inequality
        assertThat(id1).isNotEqualTo(id3);
        assertThat(id1.hashCode()).isNotEqualTo(id3.hashCode());

        // Test with null
        assertThat(id1).isNotEqualTo(null);
    }

    @Test
    void testSetters() {
        CategoryStatusId id = new CategoryStatusId();
        UUID userTestId = UUID.randomUUID();
        String categoryId = "newCategory";

        id.setUserTestId(userTestId);
        id.setCategoryId(categoryId);

        assertThat(id.getUserTestId()).isEqualTo(userTestId);
        assertThat(id.getCategoryId()).isEqualTo(categoryId);
    }
}
