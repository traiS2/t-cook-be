package com.trainh.tcookbe.repository;

import com.trainh.tcookbe.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByUsername(String username);

    Boolean existsByUsername(String username);
}
