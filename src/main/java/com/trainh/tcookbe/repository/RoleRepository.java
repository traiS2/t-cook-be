package com.trainh.tcookbe.repository;

import com.trainh.tcookbe.model.entity.Role;
import com.trainh.tcookbe.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
