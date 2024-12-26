package com.ssary.diary_web.service;

import com.ssary.diary_web.domain.User;
import com.ssary.diary_web.dto.userAuth.FindUserEmailDto;
import com.ssary.diary_web.dto.userAuth.LoginDto;

import java.util.List;

public interface UserService {

    // 로그인
    User login(LoginDto loginDto);

    // 이메일 유저정보 조회
    User findUserByEmail(String email);

    // 회원가입
    User saveUser(User user);

    // 전체 사용자 목록 불러오기
    public List<User> getUserList();

    // 사용자 인증
    public boolean permitUser(int userId);

    // 유저 이메일 조회
    public String findUserEmail(FindUserEmailDto findUserEmailDto);
}