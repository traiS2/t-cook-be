package com.trainh.tcookbe.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "primary_category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrimaryCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
