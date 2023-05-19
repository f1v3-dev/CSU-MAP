package com.seungjo.book.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    private String image;

    @Builder
    public PostsUpdateRequestDto(String title, String content, String image){
        this.title = title;
        this.content = content;
        this.image = image;
    }
}