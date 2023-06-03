package com.seungjo.book.springboot.web.dto.noticeDto;

import com.seungjo.book.springboot.domain.posts_notice.Posts_notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Posts_noticeSaveRequestDto {
    private String uuid;
    @NotBlank(message = "제목을 입력해주세요")
    private String title;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    private String author;
    private List<MultipartFile> imageFiles;




    @Builder
    public Posts_noticeSaveRequestDto(String uuid, String title, String content, String author, List<MultipartFile> imageFiles) throws IOException {
        this.uuid = uuid;
        this.title = title;
        this.content = content;
        this.author = author;
        this.imageFiles = imageFiles;

    }

    public Posts_notice toEntity(){
        return Posts_notice.builder()
                .title(title)
                .content(content)
                .author(author)
                .uuid(uuid)
                .build();
    }
}