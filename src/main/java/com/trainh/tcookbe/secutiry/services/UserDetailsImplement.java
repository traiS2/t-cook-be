
package com.trainh.tcookbe.secutiry.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trainh.tcookbe.model.entity.Account;
import com.trainh.tcookbe.model.entity.User;
import com.trainh.tcookbe.model.enums.EStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImplement implements UserDetails {
    private long id;
    private String username;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;

    private Enum<EStatus> status;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImplement() {
    }

    public UserDetailsImplement(long id, String username, String password, String firstName, String lastName, Enum<EStatus> status,  Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.authorities = authorities;
    }

    public static UserDetailsImplement build(User user, Account account) {

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImplement(
                user.getId(),
                account.getUsername(),
                account.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getStatus().getName(),
                authorities);

    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Enum<EStatus> getStatus() {
        return status;
    }

    public void setStatus(Enum<EStatus> status) {
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImplement user = (UserDetailsImplement) o;
        return Objects.equals(id, user.id);
    }
}
