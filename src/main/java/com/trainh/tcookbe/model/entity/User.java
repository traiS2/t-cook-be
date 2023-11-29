package com.trainh.tcookbe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "`user`")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", columnDefinition = "nvarchar(255)")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "nvarchar(255)")
    private String lastName;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @OneToOne(mappedBy = "user")
    private Account account;

    public User(Account account, Set<Role> roles) {
        this.account = account;
        this.roles = roles;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, Set<Role> roles, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.account = account;
    }
}
