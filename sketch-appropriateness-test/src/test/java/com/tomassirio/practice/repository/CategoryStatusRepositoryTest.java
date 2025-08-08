package com.tomassirio.practice.repository;

import com.tomassirio.practice.model.user.CategoryStatus;
import com.tomassirio.practice.model.user.CategoryStatusId;
import com.tomassirio.practice.model.testDefinitions.CategoryResult;
import com.tomassirio.practice.model.user.UserAppropriatenessTest;
import com.tomassirio.practice.model.user.SecuritySegment;
import com.tomassirio.practice.model.user.TestFlowState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryStatusRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryStatusRepository categoryStatusRepository;

    private UUID userTestId;

    @BeforeEach
    void setUp() {
        UserAppropriatenessTest userTest = new UserAppropriatenessTest();
        userTest.setUserId(UUID.randomUUID());
        userTest.setSecuritySegment(SecuritySegment.CRYPTO_ETP);
        userTest.setFlowState(TestFlowState.NOT_STARTED);
        entityManager.persist(userTest);
        userTestId = userTest.getId();
    }

    @Test
    void testSaveAndFindById() {
        CategoryStatusId id = new CategoryStatusId(userTestId, "cat1");
        CategoryStatus categoryStatus = new CategoryStatus(id, CategoryResult.PASSED, new HashMap<>());

        CategoryStatus savedCategoryStatus = categoryStatusRepository.save(categoryStatus);

        assertThat(savedCategoryStatus).isNotNull();
        assertThat(savedCategoryStatus.getId()).isEqualTo(id);

        Optional<CategoryStatus> foundCategoryStatus = categoryStatusRepository.findById(id);
        assertThat(foundCategoryStatus).isPresent();
        assertThat(foundCategoryStatus.get()).isEqualTo(savedCategoryStatus);
    }

    @Test
    void testDelete() {
        CategoryStatusId id = new CategoryStatusId(userTestId, "cat2");
        CategoryStatus categoryStatus = new CategoryStatus(id, CategoryResult.FAILED, new HashMap<>());
        entityManager.persist(categoryStatus);
        entityManager.flush();

        categoryStatusRepository.delete(categoryStatus);

        Optional<CategoryStatus> foundCategoryStatus = categoryStatusRepository.findById(id);
        assertThat(foundCategoryStatus).isNotPresent();
    }

    @Test
    void testFindAll() {
        CategoryStatusId id1 = new CategoryStatusId(userTestId, "cat3");
        CategoryStatus categoryStatus1 = new CategoryStatus(id1, CategoryResult.WARNING, new HashMap<>());
        entityManager.persist(categoryStatus1);

        CategoryStatusId id2 = new CategoryStatusId(userTestId, "cat4");
        CategoryStatus categoryStatus2 = new CategoryStatus(id2, CategoryResult.NOT_COMPLETED, new HashMap<>());
        entityManager.persist(categoryStatus2);
        entityManager.flush();

        Iterable<CategoryStatus> categoryStatuses = categoryStatusRepository.findAll();
        assertThat(categoryStatuses).hasSize(2);
    }
}