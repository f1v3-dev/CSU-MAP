package com.seungjo.book.springboot.domain.it;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface LecturesRepository extends JpaRepository<Lectures, String> {
    @Query("SELECT l FROM Lectures l, Classrooms c WHERE l.lec_name = :keyword AND l.classroom_id = c.id" )
    List<Lectures> findClassrooms(@Param("keyword") String keyword);//강의명으로 강의실 찾기

    @Query("SELECT l FROM Lectures l WHERE l.classroom_id = :keyword")
    List<Lectures> findByClassroom_id(@Param("keyword") String keyword);//강의실 번호로 강의실 찾기

    @Query("SELECT l FROM Lectures l WHERE l.lec_name LIKE %:keyword%")
    List<Lectures> findLecturesByKeyword(@Param("keyword") String keyword);

}
