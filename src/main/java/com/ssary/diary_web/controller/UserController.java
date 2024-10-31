package com.ssary.diary_web.controller;

import com.ssary.diary_web.domain.User;
import com.ssary.diary_web.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
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

    @GetMapping("/waiting")
    public String waiting(){ return "waiting";}

    @GetMapping("/list")
    public String showList(Model model){
        List<User> list = userService.getUserList();
        model.addAttribute("users",list);
        return "userList";
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

    @PostMapping("/permit")
    public String permit(@RequestParam("id") int id){
        userService.permitUser(id);
        return "redirect:/list";
    }

    @GetMapping("/regist")
    public String registform(){
        return "registform";
    }

    @PostMapping("/regist")
    public String regist(@ModelAttribute User user){
        userService.saveUser(user);
        return "redirect:/login";
    }
}
