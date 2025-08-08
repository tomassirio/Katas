package com.tomassirio.practice.model.testDefinitions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionDefinitionTest {

    @Test
    void testConstructorAndGetters() {
        TestCategoryDefinition category = new TestCategoryDefinition("CAT1", "Category One");
        QuestionDefinition question = new QuestionDefinition("What is 1+1?", "optionA", category);

        assertThat(question.getQuestionText()).isEqualTo("What is 1+1?");
        assertThat(question.getCorrectAnswerId()).isEqualTo("optionA");
        assertThat(question.getCategory()).isEqualTo(category);
    }

    @Test
    void testSetters() {
        QuestionDefinition question = new QuestionDefinition();
        TestCategoryDefinition category = new TestCategoryDefinition("CAT2", "Category Two");
        List<AnswerOption> options = new ArrayList<>();
        options.add(new AnswerOption("opt1", "Option 1", question));

        question.setId(UUID.randomUUID());
        question.setQuestionText("What is 2+2?");
        question.setCorrectAnswerId("optionB");
        question.setCategory(category);
        question.setOptions(options);

        assertThat(question.getQuestionText()).isEqualTo("What is 2+2?");
        assertThat(question.getCorrectAnswerId()).isEqualTo("optionB");
        assertThat(question.getCategory()).isEqualTo(category);
        assertThat(question.getOptions()).isEqualTo(options);
    }

    @Test
    void testNoArgsConstructor() {
        QuestionDefinition question = new QuestionDefinition();
        assertThat(question).isNotNull();
    }
}