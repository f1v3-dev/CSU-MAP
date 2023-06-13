package com.seungjo.book.springboot.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureList {
    String classroomId;
    String floor;
    String lecName;
    String professor;

    public LectureList(String classroomId, String floor, String lecName, String professor) {
        this.classroomId = classroomId;
        this.floor = floor;
        this.lecName = lecName;
        this.professor = professor;
    }
}
