package com.seungjo.book.springboot.web.dto.notice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class Posts_noticeUpdateRequestDto {
    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    @Builder
    public Posts_noticeUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;

    }
}