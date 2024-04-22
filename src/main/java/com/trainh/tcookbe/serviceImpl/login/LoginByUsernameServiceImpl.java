package com.trainh.tcookbe.serviceImpl.login;

import com.trainh.tcookbe.model.enums.EStatus;
import com.trainh.tcookbe.payload.request.login.LoginByUsernameRequest;
import com.trainh.tcookbe.payload.response.MessageResponse;
import com.trainh.tcookbe.payload.response.login.LoginByUsernameResponse;
import com.trainh.tcookbe.secutiry.jwt.JwtUtils;
import com.trainh.tcookbe.secutiry.services.UserDetailsImplement;
import com.trainh.tcookbe.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Qualifier("loginByUsernameService")
public class LoginByUsernameServiceImpl implements LoginService<LoginByUsernameRequest> {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public LoginByUsernameServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<?> login(LoginByUsernameRequest loginRequest) {


        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (AuthenticationException e) {
            if (e instanceof BadCredentialsException) {
                return ResponseEntity.status(401).body(new MessageResponse("Invalid username or password"));
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
            }
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        UserDetailsImplement userDetailsImplement = (UserDetailsImplement) authentication.getPrincipal();

        if (userDetailsImplement.getStatus().equals(EStatus.ACTIVE)) {
            LoginByUsernameResponse response = new LoginByUsernameResponse(userDetailsImplement.getUsername(), userDetailsImplement.getFirstName(), userDetailsImplement.getLastName());

            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).
                    body(response);
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Account is not active"));
        }
    }
}

