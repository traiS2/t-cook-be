package com.trainh.tcookbe.model.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IntroductionPK implements Serializable {
    private long id;
    private long blog;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntroductionPK that = (IntroductionPK) o;
        return id == that.id && blog == that.blog;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blog);
    }
}