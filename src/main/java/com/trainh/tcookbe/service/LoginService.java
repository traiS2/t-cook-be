package com.trainh.tcookbe.service;

import org.springframework.http.ResponseEntity;

public interface LoginService<T> {
    ResponseEntity<?> login(T loginRequest);
}
