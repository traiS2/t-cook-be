package com.trainh.tcookbe.service;

import com.trainh.tcookbe.model.entity.User;

import java.util.Optional;

public interface UserService {
    public Optional<User> getUserById(long id);
}
