package com.seungjo.book.springboot.service.IT;

import com.seungjo.book.springboot.domain.it.ClassroomsRepository;
import com.seungjo.book.springboot.domain.it.LecturesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.seungjo.book.springboot.domain.it.Lectures;
import com.seungjo.book.springboot.domain.it.Classrooms;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ITService {
    private final ClassroomsRepository classroomsRepository;
    private final LecturesRepository lecturesRepository;

    public List<Lectures> findClassroomsByLec_name(String keyword) { //강의명으로 강의실 찾기
        return lecturesRepository.findClassrooms(keyword);
    }

    public List<Lectures> findLecturesByKeyword(String keyword) {
        return lecturesRepository.findLecturesByKeyword(keyword);
    }

//    public List<Lectures> findAllClassrooms() {
//        return lecturesRepository.findAllClassrooms();
//    }

    public List<Classrooms> findClassrooms(Integer floor) {
        return classroomsRepository.findClassrooms(floor);
    }

    public List<Lectures> findByClassroom_id(String keyword) { //강의실 번호로 강의실 찾기
        return lecturesRepository.findByClassroom_id(keyword);
    }

}
