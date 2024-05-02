package com.trainh.tcookbe.controller;

import com.trainh.tcookbe.payload.request.login.LoginByUsernameRequest;
import com.trainh.tcookbe.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "${trainh.app.cross-origin}")
@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final LoginService<LoginByUsernameRequest> loginByUsernameService;

    @Autowired
    public LoginController(@Qualifier("loginByUsernameService") LoginService<LoginByUsernameRequest> loginByUsernameService) {
        this.loginByUsernameService = loginByUsernameService;
    }

    @PostMapping("/login-by-username")
    public ResponseEntity<?> loginByUsername(@Valid @RequestBody LoginByUsernameRequest request) {
        return loginByUsernameService.login(new LoginByUsernameRequest(request.getUsername(), request.getPassword()));
    }
}
