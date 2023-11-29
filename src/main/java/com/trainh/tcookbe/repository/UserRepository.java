package com.trainh.tcookbe.repository;

import com.trainh.tcookbe.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByAccount_Username(String username);

    Boolean existsUserByAccount_Username(String username);
}
