package com.seungjo.book.springboot.web.dto;

import com.seungjo.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String uuid;
    private String title;
    private String content;
    private String author;
    private String image;

    @Builder
    public PostsSaveRequestDto(String uuid, String title, String content, String author, String image){
        this.uuid = uuid;
        this.title = title;
        this.content = content;
        this.author = author;
        this.image = image;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .image(image)
                .uuid(uuid)
                .build();
    }
}