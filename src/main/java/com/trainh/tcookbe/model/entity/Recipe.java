package com.trainh.tcookbe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "recipe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", columnDefinition = "nvarchar(1000)")
    private String name;

    private int step;

    @Column(name = "image", columnDefinition = "varchar(500)")
    private String image;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DetailsRecipe> detailsRecipe;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog;

    public Recipe(String name, int step, String image, Set<DetailsRecipe> detailsRecipe) {
        this.name = name;
        this.step = step;
        this.image = image;
        this.detailsRecipe = detailsRecipe;
    }
}
