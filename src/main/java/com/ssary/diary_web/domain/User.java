package com.ssary.diary_web.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private Integer generate;
    @Column(name = "class")
    private Integer classId;
    private String name;
    private String email;
    private Date birth;
    private String grade = "basic";
    private String password;
    private Byte permission = 0;
    private Byte agree = 0;
}
