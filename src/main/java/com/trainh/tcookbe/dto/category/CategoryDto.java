package com.trainh.tcookbe.dto.category;

import com.trainh.tcookbe.model.entity.Blog;
import com.trainh.tcookbe.model.entity.PrimaryCategory;
import com.trainh.tcookbe.model.entity.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private int id;

    private String name;

//    private Status status;
//
//    private PrimaryCategory primaryCategory;
//
//    private Set<Blog> blogs;
}
