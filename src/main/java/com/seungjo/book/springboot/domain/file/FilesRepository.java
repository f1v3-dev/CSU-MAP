package com.seungjo.book.springboot.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilesRepository extends JpaRepository<Files, Long> {

    @Query("SELECT f FROM Files f WHERE f.postId = :postId")
    List<Files> findByPostId(@Param("postId") Long postId);
}
