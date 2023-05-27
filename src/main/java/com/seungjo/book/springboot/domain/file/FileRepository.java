package com.seungjo.book.springboot.domain.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    @Query("SELECT p FROM File p ORDER BY p.id DESC")
    List<File> findAllDesc();
}