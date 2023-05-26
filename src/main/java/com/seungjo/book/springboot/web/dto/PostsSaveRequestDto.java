package com.seungjo.book.springboot.web.dto;

import com.seungjo.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String uuid;
    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
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


    public void setImage(String image) {
        this.image = image;
    }
}