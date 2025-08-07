package com.tomassirio.practice.repository;

import com.tomassirio.practice.model.user.UserAppropriatenessTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAppropriatenessTestRepository extends JpaRepository<UserAppropriatenessTest, UUID> {
}
