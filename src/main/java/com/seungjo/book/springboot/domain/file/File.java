package com.seungjo.book.springboot.domain.file;

import com.seungjo.book.springboot.domain.BaseTimeEntity;
import com.seungjo.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class File extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="POST_ID")
    private Posts posts;

    @Column
    private String originalFileName;

    @Column
    private String savedFileName;

    private Long size;

    @Builder
    public File(String originalFileName, String savedFileName, Long size, Posts posts) {
        this.originalFileName = originalFileName;
        this.savedFileName = savedFileName;
        this.size = size;
        this.posts = posts;
    }
}
