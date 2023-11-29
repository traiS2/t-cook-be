package com.trainh.tcookbe.service;

import org.springframework.http.ResponseEntity;

public interface RegisterService<T> {
    ResponseEntity<?> register(T registerRequest);
}
