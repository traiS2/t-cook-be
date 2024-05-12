package com.trainh.tcookbe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "blog")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "link", columnDefinition = "varchar(255)", unique = true)
    private String link;

    @Column(name = "name", columnDefinition = "nvarchar(500)")
    private String name;

    @Column(name = "image", columnDefinition = "varchar(255)")
    private String image;

    @Column(name = "level_of_difficult")
    private int levelOfDifficult;

    @Column(name = "cooking_time")
    private int cookingTime;

    @Column(name = "serving_size", columnDefinition = "nvarchar(255)")
    private String servingSize;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

//    @JsonIgnore
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private Set<Introduction> introduction;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private Set<Ingredient> ingredient;

    @ManyToMany
    @JoinTable(name = "tag_blog",
            joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="tag_id", referencedColumnName = "id"))
    private Set<Tag> tag;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private Set<Recipe> recipe;

    @ManyToMany()
    @JoinTable(name = "category_blog",
            joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="category_id", referencedColumnName = "id"))
    private Set<Category> category;

    public Blog(String link, String name, String image, int levelOfDifficult, int cookingTime, String servingSize, User user, Status status, Date createAt, Set<Introduction> introduction, Set<Ingredient> ingredient, Set<Tag> tag, Set<Recipe> recipe, Set<Category> category) {
        this.link = link;
        this.name = name;
        this.image = image;
        this.levelOfDifficult = levelOfDifficult;
        this.cookingTime = cookingTime;
        this.servingSize = servingSize;
        this.user = user;
        this.status = status;
        this.createAt = createAt;
        this.introduction = introduction;
        this.ingredient = ingredient;
        this.tag = tag;
        this.recipe = recipe;
        this.category = category;
    }


}
