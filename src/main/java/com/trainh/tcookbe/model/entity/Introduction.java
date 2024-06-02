package com.trainh.tcookbe.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trainh.tcookbe.model.id.IntroductionPK;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Entity
@Table(name = "introduction")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@IdClass(IntroductionPK.class)
public class Introduction implements Serializable {
    @Id
    private long id;

    @Column(name = "content", columnDefinition = "nvarchar(500)")
    private String content;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog;

    public Introduction(String content) {
        this.content = content;
    }

    public Introduction(String content, Blog blog) {
        this.blog = blog;
        this.content = content;
    }

    public Introduction(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
