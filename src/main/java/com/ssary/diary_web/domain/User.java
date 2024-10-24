package com.ssary.diary_web.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "generate", nullable = false)
    private Integer generate;

    @Column(name = "class", nullable = false)
    private Integer classId;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "birth")
    private Date birth;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "permission", nullable = false)
    private Byte permission = 0;
}
