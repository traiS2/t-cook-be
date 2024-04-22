package com.trainh.tcookbe.serviceImpl.register;

import com.trainh.tcookbe.model.entity.Account;
import com.trainh.tcookbe.model.entity.Role;
import com.trainh.tcookbe.model.entity.Status;
import com.trainh.tcookbe.model.entity.User;
import com.trainh.tcookbe.model.enums.ERole;
import com.trainh.tcookbe.model.enums.EStatus;
import com.trainh.tcookbe.payload.request.register.RegisterByUsernameRequest;
import com.trainh.tcookbe.payload.response.MessageResponse;
import com.trainh.tcookbe.repository.AccountRepository;
import com.trainh.tcookbe.repository.RoleRepository;
import com.trainh.tcookbe.repository.StatusRepository;
import com.trainh.tcookbe.repository.UserRepository;
import com.trainh.tcookbe.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@Qualifier("registerByUsernameService")
public class RegisterByUsernameServiceImpl implements RegisterService<RegisterByUsernameRequest> {
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterByUsernameServiceImpl(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, AccountRepository accountRepository, StatusRepository statusRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.statusRepository = statusRepository;
    }
    @Override
    public ResponseEntity<?> register(RegisterByUsernameRequest registerRequest) {
        try {
            if (existsUsername(registerRequest.getUsername())) {
                ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
            }

            Set<Role> roles = new HashSet<>();


            Optional<Role> roleOptional = roleRepository.findByName(ERole.ROLE_USER);
            Optional<Status> statusOptional = statusRepository.findByName(EStatus.INACTIVE);
            if(roleOptional.isPresent() && statusOptional.isPresent()) {
                roles.add(roleOptional.get());

                User user = new User(registerRequest.getFirstName(), registerRequest.getLastName());
                user.setRoles(roles);

                user.setStatus(statusOptional.get());
                userRepository.save(user);

                Account account = new Account(registerRequest.getUsername(), passwordEncoder.encode(registerRequest.getPassword()));
                account.setUser(user);
                accountRepository.save(account);

                return ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse("User registered successfully!"));
            } else {
                ResponseEntity.badRequest().body(new MessageResponse(roleOptional.isPresent() ? "Error: Status is not found." : "Error: Role is not found."));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
        }
        return null;
    }

    private boolean existsUsername(String username) {
        return accountRepository.existsByUsername(username);
    }
}
