package com.trainh.tcookbe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", columnDefinition = "nvarchar(255)")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_category_id", referencedColumnName = "id")
    private PrimaryCategory primaryCategory;

    @JsonIgnore
    @ManyToMany()
    @JoinTable(name = "category_blog",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="blog_id", referencedColumnName = "id"))
    private Set<Blog> blogs;

    public Category(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public Category(int id) {
        this.id = id;
    }

    public Category(String name, Status status, PrimaryCategory primaryCategory) {
        this.name = name;
        this.status = status;
        this.primaryCategory = primaryCategory;
    }
}
