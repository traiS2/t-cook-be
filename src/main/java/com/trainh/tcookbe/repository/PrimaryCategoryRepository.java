package com.trainh.tcookbe.repository;

import com.trainh.tcookbe.model.entity.PrimaryCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrimaryCategoryRepository extends JpaRepository<PrimaryCategory, Long> {
    Optional<PrimaryCategory> findByName(String name);
}
