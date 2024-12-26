package com.ssary.diary_web.controller;

import com.ssary.diary_web.domain.User;
import com.ssary.diary_web.dto.userAuth.EmailDto;
import com.ssary.diary_web.dto.userAuth.FindUserEmailDto;
import com.ssary.diary_web.dto.userAuth.LoginDto;
import com.ssary.diary_web.service.MailService;
import com.ssary.diary_web.service.TokenService;
import com.ssary.diary_web.service.UserService;
import jakarta.mail.MessageRemovedException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            log.info("로그인 성공");
            return new ResponseEntity<>(user,headers,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/findEmail")
    public ResponseEntity<String> findUserEmail(@RequestBody FindUserEmailDto findUserEmailDto){
        log.info(findUserEmailDto.toString());
        String email = userService.findUserEmail(findUserEmailDto);
        if(email!=null) return new ResponseEntity<>(email,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/changeUserPassword")
    public ResponseEntity<String> changeUserPassword(@RequestBody EmailDto emailDto) throws MessageRemovedException {
        log.info(emailDto.getEmail()    );
        User user = userService.findUserByEmail(emailDto.getEmail());
        if(user==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        mailService.sendFindPassWordEmail(user.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/authEmail")
    public ResponseEntity<String> authEmail(@RequestBody EmailDto emailDto) throws MessageRemovedException{
        String code = mailService.sendAuthEmail(emailDto.getEmail());
        if(code!=null) return ResponseEntity.ok(code);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/register")
    public ResponseEntity<String> regist(@RequestBody User user){
        User newUser =  userService.saveUser(user);
        if(newUser==null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
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

}
