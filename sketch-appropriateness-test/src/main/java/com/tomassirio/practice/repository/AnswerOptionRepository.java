package com.tomassirio.practice.repository;

import com.tomassirio.practice.model.testDefinitions.AnswerOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, String> {
}
