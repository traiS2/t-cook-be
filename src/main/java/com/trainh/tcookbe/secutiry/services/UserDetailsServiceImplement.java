package com.trainh.tcookbe.secutiry.services;


import com.trainh.tcookbe.model.entity.Account;
import com.trainh.tcookbe.repository.AccountRepository;
import com.trainh.tcookbe.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImplement implements UserDetailsService{
    private final AccountRepository accountRepository;

    public UserDetailsServiceImplement(UserRepository userRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public UserDetailsImplement loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findAccountByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found with username: " + username));
        return UserDetailsImplement.build(account.getUser(), account);
    }
}
