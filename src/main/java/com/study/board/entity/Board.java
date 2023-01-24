package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity //entity는 db에서 table을 의미한다
@Data   //지양해야 할 표현, getter setter toString 전부 만들어주는 annotation
public class Board { //JPA가 이 엔티티를 읽는다
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity는 mysql, mariadb sequence는 oracle auto는 알아서 지정
    private Integer id;
    private String content;
    private Integer complete;
}

