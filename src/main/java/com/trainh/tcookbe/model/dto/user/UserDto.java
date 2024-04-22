package com.trainh.tcookbe.model.dto.user;

import com.trainh.tcookbe.model.entity.Account;
import com.trainh.tcookbe.model.entity.Role;
import com.trainh.tcookbe.model.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private int id;

    private String firstName;

    private String lastName;

    private Set<Role> roles;

    private Status status;

    private Account account;
}
