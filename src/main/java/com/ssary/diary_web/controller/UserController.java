package com.ssary.diary_web.controller;

import com.ssary.diary_web.domain.User;
import com.ssary.diary_web.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String loginform(){
        return "loginform";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpSession session){
        // userService로 유저 인증 처리
        Optional<User> loginUser = userService.login(user.getEmail(), user.getPassword());

        if (loginUser.isPresent()) {
            session.setAttribute("loginUser", loginUser.get());
            return "redirect:/"; // 로그인 성공 시 home 페이지로 리다이렉트
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
