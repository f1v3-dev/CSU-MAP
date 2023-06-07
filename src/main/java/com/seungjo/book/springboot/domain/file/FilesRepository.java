package com.seungjo.book.springboot.domain.file;

import com.seungjo.book.springboot.domain.posts_notice.Posts_notice;
import com.seungjo.book.springboot.web.dto.FileDto.FilesListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilesRepository extends JpaRepository<Files, Long> {

    @Query("SELECT f FROM Files f ORDER BY f.id DESC")
    List<Files> findAllDesc();

    @Query("SELECT f FROM Files f WHERE f.id IN (SELECT MIN(id) FROM Files GROUP BY postId)")
    List<Files> findFirstImg();

    @Query("SELECT f FROM Files f WHERE f.postId = :postId")
    List<Files> findByPostId(@Param("postId") Long postId);
}