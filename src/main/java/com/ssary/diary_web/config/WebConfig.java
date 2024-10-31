package com.ssary.diary_web.config;

import com.ssary.diary_web.Interceptor.AdminInterceptor;
import com.ssary.diary_web.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    AdminInterceptor adminInterceptor;

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 로그인 인터셉터 적용
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // 모든 경로에 대해 로그인 인터셉터 적용
                .excludePathPatterns("/login","/waiting","/regist"); // 로그인 관련 경로는 제외

        // 관리자 인터셉터 적용
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/list"); // /admin 경로에 대해서만 관리자 인터셉터 적용
    }
}
