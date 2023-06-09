package com.seungjo.book.springboot.web.dto.fileDto;

import com.seungjo.book.springboot.domain.file.Files;
import lombok.Getter;

@Getter
public class FilesListResponseDto {

    private Long id; // id
    private String originalFileName;
    private String savedFileName;
    private Long postId;

    public FilesListResponseDto(Files entity) {
        this.id = entity.getId();
        this.originalFileName = entity.getOriginalFileName();
        this.savedFileName = entity.getSavedFileName();
        this.postId = entity.getPostId();
    }

    /*

    @Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
     */
}
