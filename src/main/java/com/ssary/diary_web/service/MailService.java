package com.ssary.diary_web.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public interface MailService {

    // Random String 생성
    public String createNumber();
    
    // Message Create 메일 제작
    public MimeMessage createMail(String mail, String number)throws MessagingException;
    
    // 메일 보내버리기
    public String sendSimpleMessage(String sendEmail) throws MessagingException;

}
