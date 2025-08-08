package com.tomassirio.practice.model.testDefinitions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerOptionTest {

    @Test
    void testConstructorAndGetters() {
        QuestionDefinition question = new QuestionDefinition();
        question.setId(java.util.UUID.randomUUID());
        question.setQuestionText("Test Question");

        AnswerOption answerOption = new AnswerOption("option1", "Test Option Text", question);

        assertThat(answerOption.getOptionId()).isEqualTo("option1");
        assertThat(answerOption.getOptionText()).isEqualTo("Test Option Text");
        assertThat(answerOption.getQuestion()).isEqualTo(question);
    }

    @Test
    void testSetters() {
        AnswerOption answerOption = new AnswerOption();
        QuestionDefinition question = new QuestionDefinition();
        question.setId(java.util.UUID.randomUUID());
        question.setQuestionText("Another Question");

        answerOption.setOptionId("newOptionId");
        answerOption.setOptionText("New Option Text");
        answerOption.setQuestion(question);

        assertThat(answerOption.getOptionId()).isEqualTo("newOptionId");
        assertThat(answerOption.getOptionText()).isEqualTo("New Option Text");
        assertThat(answerOption.getQuestion()).isEqualTo(question);
    }

    @Test
    void testNoArgsConstructor() {
        AnswerOption answerOption = new AnswerOption();
        assertThat(answerOption).isNotNull();
    }
}
