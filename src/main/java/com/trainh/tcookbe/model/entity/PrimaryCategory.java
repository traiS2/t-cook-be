package com.trainh.tcookbe.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "primary_category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrimaryCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "name", columnDefinition = "nvarchar(255)")
    private String name;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @OneToMany(mappedBy = "primaryCategory")
    private Set<Category> categories;

    public PrimaryCategory(String name, Status status) {
        this.name = name;
        this.status = status;
    }
}
