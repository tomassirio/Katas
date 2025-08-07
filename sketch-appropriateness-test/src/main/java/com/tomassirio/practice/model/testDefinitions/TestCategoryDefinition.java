package com.tomassirio.practice.model.testDefinitions;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

// This entity defines a reusable test category (e.g., "Education").
@Entity
@Table(name = "test_category_definitions")
@Getter
@Setter
@NoArgsConstructor
public class TestCategoryDefinition {

    // A unique identifier for the category.
    @Id
    private String categoryId;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    // The questions belonging to this category.
    // The "mappedBy" attribute indicates that the 'category' field in the QuestionDefinition class
    // is responsible for managing the relationship.
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionDefinition> questions;

    public TestCategoryDefinition(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}