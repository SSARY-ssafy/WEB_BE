package com.ssary.diary_web.Interceptor;

import com.ssary.diary_web.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // CORS PreFlight Request
        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            return false;
        }

        // Authorization Header 검증 - Authorization header가 없거나 비정상토큰
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Authorization header is missing or invalid");
            return false;
        }

        // Token Validation Check
        String token = authorizationHeader.replace("Bearer ", "");
        if (tokenService.isExpired(token)) { // 만료된 경우에만 오류 반환
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Expired token");
            return false;
        }

        try {
            request.setAttribute("userId", tokenService.getUserId(token));
        } catch (Exception e) {
            log.error("토큰 파싱 실패: {}", e.getMessage());
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token");
            return false;
        }

        return true;
    }
}
