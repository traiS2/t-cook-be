package com.trainh.tcookbe.repository;

import com.trainh.tcookbe.model.entity.Status;
import com.trainh.tcookbe.model.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Optional<Status> findByName(EStatus name);
}
