package com.trainh.tcookbe.model.dto.account;

import com.trainh.tcookbe.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {
    private Long id;
    private String username;
    private String password;
    private User user;
}
