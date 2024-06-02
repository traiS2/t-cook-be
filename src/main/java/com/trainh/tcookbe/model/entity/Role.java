package com.trainh.tcookbe.model.entity;


import com.trainh.tcookbe.model.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Entity
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }
}
