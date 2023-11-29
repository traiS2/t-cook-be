package com.trainh.tcookbe.controller;

import com.trainh.tcookbe.config.GlobalExceptionHandler;
import com.trainh.tcookbe.payload.request.register.RegisterByUsernameRequest;
import com.trainh.tcookbe.payload.response.MessageResponse;
import com.trainh.tcookbe.service.RegisterService;
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
public class RegisterController {
    private final RegisterService<RegisterByUsernameRequest> registerByUsernameService;
    private final GlobalExceptionHandler validationExceptionHandler;

    @Autowired
    public RegisterController(@Qualifier("registerByUsernameService") RegisterService<RegisterByUsernameRequest> registerByUsernameService, GlobalExceptionHandler validationExceptionHandler) {
        this.registerByUsernameService = registerByUsernameService;
        this.validationExceptionHandler = validationExceptionHandler;
    }
    @PostMapping("/register-by-username")
    public ResponseEntity<?> registerByUsername(@Valid @RequestBody RegisterByUsernameRequest request, BindingResult bindingResult) {
        String errorMessage = validationExceptionHandler.getFirstErrorMessage(bindingResult);
        if(errorMessage != null) {
            return ResponseEntity.badRequest().body(new MessageResponse(errorMessage));
        } else {
            return registerByUsernameService.register(request);
        }
    }
}
