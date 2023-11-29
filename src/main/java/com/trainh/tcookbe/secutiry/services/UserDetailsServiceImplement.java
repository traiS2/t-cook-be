package com.trainh.tcookbe.secutiry.services;


import com.trainh.tcookbe.model.entity.User;
import com.trainh.tcookbe.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImplement implements UserDetailsService{
    private final UserRepository userRepository;

    public UserDetailsServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDetailsImplement loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByAccount_Username(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return UserDetailsImplement.build(user);
    }
}
