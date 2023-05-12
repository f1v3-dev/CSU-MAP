package com.seungjo.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private String image;

    @Builder
    public PostsUpdateRequestDto(String title, String content, String image){
        this.title = title;
        this.content = content;
        this.image = image;
    }
}