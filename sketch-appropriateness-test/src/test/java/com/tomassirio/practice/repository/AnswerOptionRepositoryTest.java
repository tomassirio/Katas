package com.tomassirio.practice.repository;

import com.tomassirio.practice.model.testDefinitions.AnswerOption;
import com.tomassirio.practice.model.testDefinitions.QuestionDefinition;
import com.tomassirio.practice.model.testDefinitions.TestCategoryDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AnswerOptionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AnswerOptionRepository answerOptionRepository;

    private QuestionDefinition question;

    @BeforeEach
    void setUp() {
        TestCategoryDefinition category = new TestCategoryDefinition("CAT1", "Category One");
        entityManager.persist(category);

        question = new QuestionDefinition("What is 1+1?", "optionA", category);
        entityManager.persist(question);
    }

    @Test
    void testSaveAndFindById() {
        AnswerOption answerOption = new AnswerOption("opt1", "Option 1", question);
        AnswerOption savedOption = answerOptionRepository.save(answerOption);

        assertThat(savedOption).isNotNull();
        assertThat(savedOption.getOptionId()).isEqualTo("opt1");

        Optional<AnswerOption> foundOption = answerOptionRepository.findById("opt1");
        assertThat(foundOption).isPresent();
        assertThat(foundOption.get()).isEqualTo(savedOption);
    }

    @Test
    void testDelete() {
        AnswerOption answerOption = new AnswerOption("opt2", "Option 2", question);
        entityManager.persist(answerOption);
        entityManager.flush();

        answerOptionRepository.delete(answerOption);

        Optional<AnswerOption> foundOption = answerOptionRepository.findById("opt2");
        assertThat(foundOption).isNotPresent();
    }

    @Test
    void testFindAll() {
        AnswerOption option1 = new AnswerOption("opt3", "Option 3", question);
        AnswerOption option2 = new AnswerOption("opt4", "Option 4", question);
        entityManager.persist(option1);
        entityManager.persist(option2);
        entityManager.flush();

        Iterable<AnswerOption> answerOptions = answerOptionRepository.findAll();
        assertThat(answerOptions).hasSize(2);
    }
}
