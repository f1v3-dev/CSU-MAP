package com.seungjo.book.springboot.domain.posts_notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Posts_noticeRepository extends JpaRepository<Posts_notice, Long> {
    @Query("SELECT p FROM Posts_notice p ORDER BY p.id DESC")
    List<Posts_notice> findAllDesc();
    List<Posts_notice> findByTitleContaining(String keyword);
}
