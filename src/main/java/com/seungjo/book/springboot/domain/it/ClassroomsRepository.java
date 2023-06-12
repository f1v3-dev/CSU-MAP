package com.seungjo.book.springboot.domain.it;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ClassroomsRepository extends JpaRepository<Classrooms, String> {
    @Query("SELECT c FROM Classrooms c WHERE c.name = :keyword")
    List<Classrooms> findClassrooms(@Param("keyword")String keyword);//강의실 이름으로 강의실 찾기
}
