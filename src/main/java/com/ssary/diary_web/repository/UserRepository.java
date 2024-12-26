package com.ssary.diary_web.repository;

import com.ssary.diary_web.domain.User;
import com.ssary.diary_web.dto.userAuth.FindUserEmailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "u.name = :name "
            + "AND u.generate = :generate "
            + "AND u.classId = :classId "
            + "AND u.birth = :birth")
    Optional<User> findUserByParams(
            @Param("name") String name,
            @Param("generate") Integer generate,
            @Param("classId") Integer classId,
            @Param("birth") LocalDate birth
    );
}
