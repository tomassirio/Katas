package com.tomassirio.practice.model.testDefinitions;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// This entity defines a possible answer for a question.
@Entity
@Table(name = "answer_options")
public class AnswerOption {

    @Id
    private String optionId; // Use a simple ID for the option.

    @Column(name = "option_text", nullable = false)
    private String optionText;

    // The question this option belongs to.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionDefinition question;

}