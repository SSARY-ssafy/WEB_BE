package com.ssary.diary_web.controller;

import com.ssary.diary_web.service.MailServiceImpl;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailServiceImpl mailService;

    @ResponseBody
    @PostMapping("/emailCheck") // 이 부분은 각자 바꿔주시면 됩니다.
    public String emailCheck(@RequestParam("email") String email) throws MessagingException, UnsupportedEncodingException {
        String authCode = mailService.sendSimpleMessage(email);
        return authCode; // Response body에 값을 반환
    }
}