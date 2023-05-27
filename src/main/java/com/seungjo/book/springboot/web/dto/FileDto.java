package com.seungjo.book.springboot.web.dto;

import com.seungjo.book.springboot.domain.file.File;
import lombok.Builder;
import lombok.Data;

@Data
public class FileDto {

    private Long id; // id
    private String originalFileName;
    private String savedFileName;
    private Long size;

    @Builder
    public FileDto(Long id, String originalFileName, String savedFileName, Long size) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;
        this.size = size;
    }

    public File toEntity() {
        return File.builder()
                .originalFileName(originalFileName)
                .savedFileName(savedFileName)
                .size(size)
                .build();
    }
}
