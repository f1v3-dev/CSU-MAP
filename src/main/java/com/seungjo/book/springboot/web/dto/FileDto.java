package com.seungjo.book.springboot.web.dto;

import com.seungjo.book.springboot.domain.file.File;
import com.seungjo.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Data;

@Data
public class FileDto {

    private Long id; // id
    private String originalFileName;
    private String savedFileName;
    private Long size;
    private Posts posts;

    @Builder
    public FileDto(Long id, String originalFileName, String savedFileName, Long size, Posts posts) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;
        this.size = size;
        this.posts = posts;
    }

    public FileDto(File entity) {
        this.id = entity.getId();
        this.originalFileName = entity.getOriginalFileName();
        this.savedFileName = entity.getSavedFileName();
        this.size = entity.getSize();
        this.posts = entity.getPosts();
    }

    public File toEntity() {
        return File.builder()
                .originalFileName(originalFileName)
                .savedFileName(savedFileName)
                .size(size)
                .posts(posts)
                .build();
    }
}
