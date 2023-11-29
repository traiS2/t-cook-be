package com.trainh.tcookbe.repository;

import com.trainh.tcookbe.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    public Optional<Tag> findById(int id);
    public Optional<Tag> findByName(String name);
}
