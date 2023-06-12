package com.seungjo.book.springboot.domain.it;


import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Classrooms {
    @Id
    private String id;

    @Column
    private String name;
}
