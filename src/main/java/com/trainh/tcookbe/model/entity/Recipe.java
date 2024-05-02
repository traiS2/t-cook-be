package com.trainh.tcookbe.model.entity;

import com.trainh.tcookbe.model.id.RecipePK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "recipe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(RecipePK.class)
public class Recipe implements Serializable {
    @Id
    private long id;

    @Column(name = "name", columnDefinition = "nvarchar(1000)")
    private String name;

    @Column(name = "image", columnDefinition = "varchar(500)")
    private String image;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DetailRecipe> detailRecipe;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog;

    public Recipe(long id, String name, int step, String image, Set<DetailRecipe> detailRecipe) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.detailRecipe = detailRecipe;
    }

    public Recipe(String name, int step, String image, Set<DetailRecipe> detailRecipe) {
        this.name = name;
        this.image = image;
        this.detailRecipe = detailRecipe;
    }
}
