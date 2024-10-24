package com.ssary.diary_web.service;

import com.ssary.diary_web.domain.User;

import java.util.Optional;

public interface UserService {

    // 로그인
    Optional<User> login(String email, String password);

    // 회원가입
    User saveUser(User user);
}