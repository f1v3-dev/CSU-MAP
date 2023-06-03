package com.seungjo.book.springboot.web.dto.noticeDto;

import com.seungjo.book.springboot.domain.posts_notice.Posts_notice;
import lombok.Getter;

@Getter
public class Posts_noticeListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String modifiedDate;

    public Posts_noticeListResponseDto(Posts_notice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}