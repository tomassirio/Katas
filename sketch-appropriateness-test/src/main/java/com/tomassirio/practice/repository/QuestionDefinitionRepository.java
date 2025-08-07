package com.tomassirio.practice.repository;

import com.tomassirio.practice.model.testDefinitions.QuestionDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionDefinitionRepository extends JpaRepository<QuestionDefinition, UUID> {
}
