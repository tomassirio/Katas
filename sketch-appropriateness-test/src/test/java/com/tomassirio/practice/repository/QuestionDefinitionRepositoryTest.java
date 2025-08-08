package com.tomassirio.practice.repository;

import com.tomassirio.practice.model.testDefinitions.QuestionDefinition;
import com.tomassirio.practice.model.testDefinitions.TestCategoryDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class QuestionDefinitionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private QuestionDefinitionRepository questionDefinitionRepository;

    private TestCategoryDefinition category;

    @BeforeEach
    void setUp() {
        category = new TestCategoryDefinition("CAT1", "Category One");
        entityManager.persist(category);
    }

    @Test
    void testSaveAndFindById() {
        QuestionDefinition question = new QuestionDefinition("What is 1+1?", "optionA", category);
        QuestionDefinition savedQuestion = questionDefinitionRepository.save(question);

        assertThat(savedQuestion).isNotNull();
        assertThat(savedQuestion.getId()).isNotNull();

        Optional<QuestionDefinition> foundQuestion = questionDefinitionRepository.findById(savedQuestion.getId());
        assertThat(foundQuestion).isPresent();
        assertThat(foundQuestion.get()).isEqualTo(savedQuestion);
    }

    @Test
    void testDelete() {
        QuestionDefinition question = new QuestionDefinition("What is 2+2?", "optionB", category);
        entityManager.persist(question);
        entityManager.flush();

        questionDefinitionRepository.delete(question);

        Optional<QuestionDefinition> foundQuestion = questionDefinitionRepository.findById(question.getId());
        assertThat(foundQuestion).isNotPresent();
    }

    @Test
    void testFindAll() {
        QuestionDefinition question1 = new QuestionDefinition("Q1", "A1", category);
        QuestionDefinition question2 = new QuestionDefinition("Q2", "A2", category);
        entityManager.persist(question1);
        entityManager.persist(question2);
        entityManager.flush();

        Iterable<QuestionDefinition> questions = questionDefinitionRepository.findAll();
        assertThat(questions).hasSize(2);
    }
}
