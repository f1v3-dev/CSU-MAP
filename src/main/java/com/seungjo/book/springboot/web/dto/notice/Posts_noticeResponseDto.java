package com.seungjo.book.springboot.web.dto.notice;

import com.seungjo.book.springboot.domain.posts_notice.Posts_notice;
import lombok.Getter;

@Getter
public class Posts_noticeResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private String uuid;

    public Posts_noticeResponseDto(Posts_notice entity) {
        this.uuid = entity.getUuid();
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}