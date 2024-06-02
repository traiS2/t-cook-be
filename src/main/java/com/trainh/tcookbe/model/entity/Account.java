package com.trainh.tcookbe.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;


@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
