package com.seungjo.book.springboot.domain.posts;

import com.seungjo.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    private String image;

    private String uuid;

    @Builder
    public Posts(String title, String content, String author, String image, String uuid){
        this.title = title;
        this.content = content;
        this.author = author;
        this.image = image;
        this.uuid = uuid;
    }

    public void update(String title, String content, String image){
        this.title = title;
        this.content = content;
        this.image = image;
    }
}