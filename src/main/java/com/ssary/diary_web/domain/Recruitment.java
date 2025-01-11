package com.ssary.diary_web.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "recruitment")
@Data
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id")
    private Integer recruitId;

    @Column(name = "name")
    private String name;

    @Column(name = "start")
    private java.sql.Date start;

    @Column(name = "end")
    private java.sql.Date end;

    @Column(name = "link")
    private String link;
}
