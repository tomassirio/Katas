package com.tomassirio.practice.model.testDefinitions;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "question_definitions")
@Getter
@Setter
@NoArgsConstructor
public class QuestionDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    // The ID of the correct answer option.
    @Column(name = "correct_answer_id")
    private String correctAnswerId;

    // The category this question belongs to.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private TestCategoryDefinition category;

    // The possible answers for this question.
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerOption> options;

    public QuestionDefinition(String questionText, String correctAnswerId, TestCategoryDefinition category) {
        this.questionText = questionText;
        this.correctAnswerId = correctAnswerId;
        this.category = category;
    }
}