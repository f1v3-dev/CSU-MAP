package com.seungjo.book.springboot.web.dto.postDto;

import com.seungjo.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private String uuid;

    public PostsResponseDto(Posts entity) {
        this.uuid = entity.getUuid();
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}