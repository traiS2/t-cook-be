package com.trainh.tcookbe.model.entity;

import com.trainh.tcookbe.model.id.DetailRecipePK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "detail_recipe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(DetailRecipePK.class)
public class DetailRecipe implements Serializable {

    @Id
    private long id;

    @Column(name = "content", columnDefinition = "nvarchar(1000)")
    private String content;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns(
            {@JoinColumn(name = "recipe_id", referencedColumnName = "id"),
                    @JoinColumn(name = "blog_id", referencedColumnName = "blog_id")}

    )
    private Recipe recipe;

    public DetailRecipe(String content) {
        this.content = content;
    }

    public DetailRecipe(String content, Recipe recipe) {
        this.content = content;
        this.recipe = recipe;
    }

    public DetailRecipe(long id, String content) {
        this.id = id;
        this.content = content;
    }

}
