package com.seungjo.book.springboot.domain.file;

import com.seungjo.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Files extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;

    @Column
    private String savedFileName;
    private Long size;
    private Long postId;

    @Builder
    public Files(String originalFileName, String savedFileName, Long size, Long postId) {
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;
        this.size = size;
        this.postId = postId;
    }
}
