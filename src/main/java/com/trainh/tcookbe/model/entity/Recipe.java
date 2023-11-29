package com.trainh.tcookbe.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "recipe")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", columnDefinition = "nvarchar(1000)")
    private String title;

    private int step;

    @Column(name = "name", columnDefinition = "nvarchar(1000)")
    private String description;

    @Column(name = "image", columnDefinition = "varchar(200)")
    private String image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog;

    public Recipe(String title, int step, String description, String image, Blog blog) {
        this.title = title;
        this.step = step;
        this.description = description;
        this.blog = blog;
        this.image = image;
    }
}
