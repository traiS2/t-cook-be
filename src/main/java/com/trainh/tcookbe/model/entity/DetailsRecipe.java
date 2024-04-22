package com.trainh.tcookbe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "details_recipe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetailsRecipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content", columnDefinition = "nvarchar(1000)")
    private String content;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    private Recipe recipe;

    public DetailsRecipe(String content) {
        this.content = content;
    }

public DetailsRecipe(String content, Recipe recipe) {
        this.content = content;
        this.recipe = recipe;
    }

}
