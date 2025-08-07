package com.tomassirio.practice.model.user;

import com.tomassirio.practice.model.converter.UserAnswersConverter;
import com.tomassirio.practice.model.testDefinitions.CategoryResult;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Map;

@Entity
@Table(name = "user_category_statuses")
public class CategoryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EmbeddedId
    private CategoryStatusId id;

    // The result for this category, stored as a string.
    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false)
    private CategoryResult result;

    // Stores the user's answers for review. Using JSONB in PostgreSQL is ideal for this map.
    @Convert(converter = UserAnswersConverter.class)
    @Column(name = "user_answers", columnDefinition = "jsonb")
    private Map<String, String> userAnswers;
}
