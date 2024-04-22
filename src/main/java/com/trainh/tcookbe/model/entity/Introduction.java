package com.trainh.tcookbe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "introduction")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Introduction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "content", columnDefinition = "nvarchar(500)")
    private String content;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")

    private Blog blog;

    public Introduction(String content) {
        this.content = content;
    }

    public Introduction(String content, Blog blog) {
        this.content = content;
        this.blog = blog;
    }
}
