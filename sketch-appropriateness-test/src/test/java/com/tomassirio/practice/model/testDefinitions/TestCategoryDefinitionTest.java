package com.tomassirio.practice.model.testDefinitions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TestCategoryDefinitionTest {

    @Test
    void testConstructorAndGetters() {
        TestCategoryDefinition category = new TestCategoryDefinition("CAT1", "Category One");

        assertThat(category.getCategoryId()).isEqualTo("CAT1");
        assertThat(category.getCategoryName()).isEqualTo("Category One");
        assertThat(category.getQuestions()).isNull(); // Should be null initially
    }

    @Test
    void testSetters() {
        TestCategoryDefinition category = new TestCategoryDefinition();
        List<QuestionDefinition> questions = new ArrayList<>();
        questions.add(new QuestionDefinition("Q1", "A1", category));

        category.setCategoryId("NEW_CAT");
        category.setCategoryName("New Category Name");
        category.setQuestions(questions);

        assertThat(category.getCategoryId()).isEqualTo("NEW_CAT");
        assertThat(category.getCategoryName()).isEqualTo("New Category Name");
        assertThat(category.getQuestions()).isEqualTo(questions);
    }

    @Test
    void testNoArgsConstructor() {
        TestCategoryDefinition category = new TestCategoryDefinition();
        assertThat(category).isNotNull();
    }
}
