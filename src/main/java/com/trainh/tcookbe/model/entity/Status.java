package com.trainh.tcookbe.model.entity;

import com.trainh.tcookbe.model.enums.EStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Entity
@Table(name = "status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EStatus name;

    public Status(EStatus name) {
        this.name = name;
    }
}
