package com.seungjo.book.springboot.web.dto.findDto;


import lombok.Builder;
import lombok.Data;

@Data
public class FindDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private String originalFileName;
    private String savedFileName;
    private String modifiedDate;

    @Builder
    public FindDto(Long id, String title, String author, String content, String originalFileName, String savedFileName, String modifiedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;
        this.modifiedDate = modifiedDate;
    }

}
