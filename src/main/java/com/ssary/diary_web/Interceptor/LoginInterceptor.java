package com.ssary.diary_web.Interceptor;

import com.ssary.diary_web.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        // 세션에 로그인 정보가 없을 경우 로그인 페이지로 리다이렉트
        if (session == null || session.getAttribute("loginUser") == null ) {
            response.sendRedirect("/login");
            return false; // 더 이상 요청 처리하지 않음
        }

        // 세션에 로그인 유저가 대기 유저 일 때 대기 중입니다로 Redirect 처리
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser.getPermission() == 0){
            response.sendRedirect("/waiting");
            return false;
        }

        return true;
    }
}
