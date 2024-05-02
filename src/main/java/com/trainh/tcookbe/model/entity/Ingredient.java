package com.trainh.tcookbe.model.entity;

import com.trainh.tcookbe.model.id.IngredientPk;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ingredient")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(IngredientPk.class)
public class Ingredient {
    @Id
    private long id;

    @Column(name = "name", columnDefinition = "nvarchar(255)")
    private String name;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog;

    public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
