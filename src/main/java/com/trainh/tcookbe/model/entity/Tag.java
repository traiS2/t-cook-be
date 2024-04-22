package com.trainh.tcookbe.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tag", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", columnDefinition = "nvarchar(255)")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tag_blog",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="blog_id", referencedColumnName = "id"))
    private Set<Blog> blogs;

    public Tag(String name) {
        this.name = name;
    }

    public Tag(long id) {
        this.id = id;
    }

    public Tag(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
