package com.seungjo.book.springboot.domain.it;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Lectures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String lec_name;

    @Column
    private String division;

    @Column
    private String start;

    @Column
    private String end;

    @Column
    private String day;

    @Column
    private String classroom_id;

    @Column
    private String professor;

}
