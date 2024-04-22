package com.trainh.tcookbe.model.projection.user;

public interface UserFullNameProjection {
    String getFirstName();
    String getLastName();

default String getFullName() {
        return getFirstName();
    }
}
