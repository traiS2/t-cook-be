package com.trainh.tcookbe.payload.request.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterByUsernameRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
