package com.trainh.tcookbe.payload.response.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginByUsernameResponse {
    private String username;
    private String firstName;
    private String lastName;
}
