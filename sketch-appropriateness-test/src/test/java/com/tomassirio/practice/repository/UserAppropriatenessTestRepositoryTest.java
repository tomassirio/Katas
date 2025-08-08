package com.tomassirio.practice.repository;

import com.tomassirio.practice.model.user.UserAppropriatenessTest;
import com.tomassirio.practice.model.user.SecuritySegment;
import com.tomassirio.practice.model.user.TestFlowState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserAppropriatenessTestRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserAppropriatenessTestRepository userAppropriatenessTestRepository;

    @Test
    void testSaveAndFindById() {
        UserAppropriatenessTest userTest = new UserAppropriatenessTest();
        userTest.setUserId(UUID.randomUUID());
        userTest.setSecuritySegment(SecuritySegment.CRYPTO_ETP);
        userTest.setFlowState(TestFlowState.NOT_STARTED);

        UserAppropriatenessTest savedUserTest = userAppropriatenessTestRepository.save(userTest);

        assertThat(savedUserTest).isNotNull();
        assertThat(savedUserTest.getId()).isNotNull();

        Optional<UserAppropriatenessTest> foundUserTest = userAppropriatenessTestRepository.findById(savedUserTest.getId());
        assertThat(foundUserTest).isPresent();
        assertThat(foundUserTest.get()).isEqualTo(savedUserTest);
    }

    @Test
    void testDelete() {
        UserAppropriatenessTest userTest = new UserAppropriatenessTest();
        userTest.setUserId(UUID.randomUUID());
        userTest.setSecuritySegment(SecuritySegment.COMPLEX_ETC);
        userTest.setFlowState(TestFlowState.COMPLETED_PASSED);
        entityManager.persist(userTest);
        entityManager.flush();

        userAppropriatenessTestRepository.delete(userTest);

        Optional<UserAppropriatenessTest> foundUserTest = userAppropriatenessTestRepository.findById(userTest.getId());
        assertThat(foundUserTest).isNotPresent();
    }

    @Test
    void testFindAll() {
        UserAppropriatenessTest userTest1 = new UserAppropriatenessTest();
        userTest1.setUserId(UUID.randomUUID());
        userTest1.setSecuritySegment(SecuritySegment.CRYPTO_ETP);
        userTest1.setFlowState(TestFlowState.NOT_STARTED);
        entityManager.persist(userTest1);

        UserAppropriatenessTest userTest2 = new UserAppropriatenessTest();
        userTest2.setUserId(UUID.randomUUID());
        userTest2.setSecuritySegment(SecuritySegment.COMPLEX_ETC);
        userTest2.setFlowState(TestFlowState.IN_PROGRESS);
        entityManager.persist(userTest2);
        entityManager.flush();

        Iterable<UserAppropriatenessTest> userTests = userAppropriatenessTestRepository.findAll();
        assertThat(userTests).hasSize(2);
    }
}
