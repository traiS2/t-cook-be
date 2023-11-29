package com.trainh.tcookbe.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "blog")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "nvarchar(500)")
    private String name;

    @Column(name = "image", columnDefinition = "varchar(255)")
    private String image;

    @Column(name = "introduction", columnDefinition = "nvarchar(1000)")
    private String introduction;

    @Column(name = "level_of_difficult")
    @Size(min = 1, max = 5)
    private int levelOfDifficult;

    @Column(name = "cooking_time")
    private int cookingTime;

    @Column(name = "serving_size", columnDefinition = "nvarchar(255)")
    private String serving_size;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ingredient> ingredient;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tag_blog",
            joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="tag_id", referencedColumnName = "id"))
    private Set<Tag> tags;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Recipe> recipes;

    @ManyToMany
    @JoinTable(name = "category_blog",
            joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="category_id", referencedColumnName = "id"))
    private Set<Category> categories;

    public Blog(String name, String image, String introduction, int levelOfDifficult, int cookingTime, String serving_size, Status status, Date createAt, User user) {
        this.name = name;
        this.image = image;
        this.introduction = introduction;
        this.levelOfDifficult = levelOfDifficult;
        this.cookingTime = cookingTime;
        this.serving_size = serving_size;
        this.status = status;
        this.createAt = createAt;
        this.user = user;
    }
}
