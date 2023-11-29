package com.trainh.tcookbe.repository;

import com.trainh.tcookbe.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDetailsRepository extends JpaRepository<Recipe, Integer> {
}
