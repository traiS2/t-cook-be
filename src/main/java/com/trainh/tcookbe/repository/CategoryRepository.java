package com.trainh.tcookbe.repository;

import com.trainh.tcookbe.model.entity.Category;
import com.trainh.tcookbe.model.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);
    List<Category> findAllByStatusName(EStatus status);
}
