package com.tomassirio.practice.repository;

import com.tomassirio.practice.model.testDefinitions.TestCategoryDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TestCategoryDefinitionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TestCategoryDefinitionRepository testCategoryDefinitionRepository;

    @Test
    void testSaveAndFindById() {
        TestCategoryDefinition category = new TestCategoryDefinition("CAT1", "Category One");
        TestCategoryDefinition savedCategory = testCategoryDefinitionRepository.save(category);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getCategoryId()).isEqualTo("CAT1");

        Optional<TestCategoryDefinition> foundCategory = testCategoryDefinitionRepository.findById("CAT1");
        assertThat(foundCategory).isPresent();
        assertThat(foundCategory.get()).isEqualTo(savedCategory);
    }

    @Test
    void testDelete() {
        TestCategoryDefinition category = new TestCategoryDefinition("CAT2", "Category Two");
        entityManager.persist(category);
        entityManager.flush();

        testCategoryDefinitionRepository.delete(category);

        Optional<TestCategoryDefinition> foundCategory = testCategoryDefinitionRepository.findById("CAT2");
        assertThat(foundCategory).isNotPresent();
    }

    @Test
    void testFindAll() {
        TestCategoryDefinition category1 = new TestCategoryDefinition("CAT3", "Category Three");
        TestCategoryDefinition category2 = new TestCategoryDefinition("CAT4", "Category Four");
        entityManager.persist(category1);
        entityManager.persist(category2);
        entityManager.flush();

        Iterable<TestCategoryDefinition> categories = testCategoryDefinitionRepository.findAll();
        assertThat(categories).hasSize(2);
    }
}
