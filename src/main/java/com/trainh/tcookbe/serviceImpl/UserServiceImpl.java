package com.trainh.tcookbe.serviceImpl;

import com.trainh.tcookbe.model.entity.User;
import com.trainh.tcookbe.repository.UserRepository;
import com.trainh.tcookbe.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
