package com.ssary.diary_web.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        // 세션에 로그인 정보가 없을 경우 로그인 페이지로 리다이렉트
        if (session == null || session.getAttribute("loginUser") == null) {
            response.sendRedirect("/login");
            return false; // 더 이상 요청 처리하지 않음
        }

        return true;
    }
}
