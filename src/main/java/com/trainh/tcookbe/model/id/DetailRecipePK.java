package com.trainh.tcookbe.model.id;

import com.trainh.tcookbe.model.entity.Recipe;

import java.io.Serializable;
import java.util.Objects;


public class DetailRecipePK implements Serializable {
    private long id;

    private Recipe recipe;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailRecipePK that = (DetailRecipePK) o;
        return id == that.id && recipe.equals(that.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipe);
    }
}


