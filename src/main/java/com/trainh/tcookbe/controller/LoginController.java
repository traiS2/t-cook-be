package com.trainh.tcookbe.controller;

import com.trainh.tcookbe.config.GlobalExceptionHandler;
import com.trainh.tcookbe.payload.request.login.LoginByUsernameRequest;
import com.trainh.tcookbe.payload.response.MessageResponse;
import com.trainh.tcookbe.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "${trainh.app.cross-origin}")
@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final LoginService<LoginByUsernameRequest> loginByUsernameService;
    private final GlobalExceptionHandler validationExceptionHandler;

    @Autowired
    public LoginController(@Qualifier("loginByUsernameService") LoginService<LoginByUsernameRequest> loginByUsernameService, GlobalExceptionHandler validationExceptionHandler) {
        this.loginByUsernameService = loginByUsernameService;
        this.validationExceptionHandler = validationExceptionHandler;
    }

    @PostMapping("/login-by-username")
    public ResponseEntity<?> loginByUsername(@Valid @RequestBody LoginByUsernameRequest request, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if (errorMessage != null) {
            return ResponseEntity.badRequest().body(new MessageResponse(errorMessage));
        } else {
            return loginByUsernameService.login(new LoginByUsernameRequest(request.getUsername(), request.getPassword()));
        }
    }
}
