package com.tomassirio.practice.model.user;

import com.tomassirio.practice.model.converter.UserAnswersConverter;
import com.tomassirio.practice.model.testDefinitions.CategoryResult;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_category_statuses")
public class CategoryStatus {

    // The composite primary key is defined by the CategoryStatusId class.
    // The previous @Id and @GeneratedValue were conflicting and have been removed.
    @EmbeddedId
    private CategoryStatusId id;

    // The result for this category, stored as a string.
    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false)
    private CategoryResult result;

    // Stores the user's answers for review. Using JSONB in PostgreSQL is ideal for this map.
    // Change to JsonB when using Postgre
    @Convert(converter = UserAnswersConverter.class)
    @Column(name = "user_answers", columnDefinition = "clob")
    private Map<String, String> userAnswers;
}

