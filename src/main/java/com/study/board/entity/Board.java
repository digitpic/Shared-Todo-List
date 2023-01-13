/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity //entity는 db에서 table을 의미한다
public class Board { //JPA가 이 엔티티를 읽는다
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identity는 mysql, mariadb sequence는 oracle auto는 알아서 지정
    private Integer id;
    private String title;
    private String content;
}
