package com.ssary.diary_web.controller;

import com.ssary.diary_web.domain.User;
import com.ssary.diary_web.dto.userAuth.LoginDto;
import com.ssary.diary_web.service.MailService;
import com.ssary.diary_web.service.TokenService;
import com.ssary.diary_web.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MailService mailService;
    private final TokenService tokenService;

    @GetMapping("/")
    public ResponseEntity<?> getUserList() {
        List<User> list = userService.getUserList();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginDto loginDto){
        // 로그인 성공
        if(userService.login(loginDto)!=null) {
            User user = userService.findUserByEmail(loginDto.getEmail());
            String token = tokenService.createToken(String.valueOf(user.getUserId())); // JWT 생성
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token); // Authorization 헤더에 JWT 토큰 추가
            user.setPassword("");
            return new ResponseEntity<>(user,headers,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/waiting")
    public String waiting(){ return "waiting";}

    @GetMapping("/list")
    public String showList(Model model){
        List<User> list = userService.getUserList();
        model.addAttribute("users",list);
        return "userList";
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
