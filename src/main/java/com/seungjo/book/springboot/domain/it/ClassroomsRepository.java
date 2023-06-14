package com.seungjo.book.springboot.domain.it;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ClassroomsRepository extends JpaRepository<Classrooms, String> {
//    @Query("SELECT c FROM Classrooms c WHERE c.name = :keyword")
//    List<Classrooms> findClassrooms(@Param("keyword")String keyword);//강의실 이름으로 강의실 찾기


    @Query("SELECT c FROM Classrooms c WHERE (c.id LIKE CONCAT(:floor, '_%') AND c.id NOT LIKE CONCAT(:floor, '0%')) OR (c.id LIKE CONCAT(:floor, '1%')) OR (c.id LIKE CONCAT(:floor, '2%'))")
    List<Classrooms> findClassrooms(@Param("floor") Integer floor);





}
