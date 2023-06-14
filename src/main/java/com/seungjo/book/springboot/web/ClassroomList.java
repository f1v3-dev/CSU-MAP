package com.seungjo.book.springboot.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassroomList {
    String id;
    String name;

    public ClassroomList(String id, String name){
        this.id = id;
        this.name = name;
    }
}
