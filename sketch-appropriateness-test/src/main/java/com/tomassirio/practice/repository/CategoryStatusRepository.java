package com.tomassirio.practice.repository;

import com.tomassirio.practice.model.user.CategoryStatus;
import com.tomassirio.practice.model.user.CategoryStatusId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryStatusRepository extends JpaRepository<CategoryStatus, CategoryStatusId> {
}
