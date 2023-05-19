package com.study.boardAPI.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// JPA가 읽어드림
@Entity // 테이블 어노테이션
@Data // lombok 사용 -> 다른 클래스에 참조 가능 (Getter 자동 생성)
public class Board {

    @Id // Primary Key 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql, mariaDB 사용
    // 테이블의 column
    private Integer id;

    private String title;

    private String content;

    private String filename;

    private String filepath;

}
