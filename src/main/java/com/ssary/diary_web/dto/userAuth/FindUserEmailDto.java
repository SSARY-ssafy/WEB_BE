package com.ssary.diary_web.dto.userAuth;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@ToString
@Getter
@Setter
public class FindUserEmailDto {
    private String name;
    private Integer generate;
    private Integer classId;
    private LocalDate birth;
}
