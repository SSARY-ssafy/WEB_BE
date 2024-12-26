package com.ssary.diary_web.service;

import com.ssary.diary_web.domain.User;
import jakarta.mail.MessageRemovedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final UserService userService;

    public void sendFindPassWordEmail(String email) throws MessageRemovedException{
        MimeMessagePreparator preparator = mimeMessage -> {

            // HTML 템플릿 로드
            String htmlContent = loadHtmlTemplate("static/findPassword-template.html");

            String newPassword = changePassword(email);
            htmlContent = htmlContent.replace("{{number}}", newPassword);

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            // 받는 사람 이메일
            helper.setTo(email);
            // 이메일 제목
            helper.setSubject("비밀번호 변경 알림");
            // 메일 내용
            helper.setText(htmlContent, true);  // HTML 형식으로 설정
        };
        try {
            log.info(email);
            this.mailSender.send(preparator);
        } catch (MailException err) {
            log.info("ERROR: Message 전송 실패");
            throw err;
        }
    }

    public String sendAuthEmail(String email) throws MessageRemovedException{
        String authCode = randomString();
        MimeMessagePreparator preparator = mimeMessage -> {

            // HTML 템플릿 로드
            String htmlContent = loadHtmlTemplate("static/regist-template.html");

            htmlContent = htmlContent.replace("{{number}}", authCode);

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            // 받는 사람 이메일
            helper.setTo(email);
            // 이메일 제목
            helper.setSubject("비밀번호 변경 알림");
            // 메일 내용
            helper.setText(htmlContent, true);  // HTML 형식으로 설정
        };
        try {
            log.info(email);
            this.mailSender.send(preparator);
            return authCode;
        } catch (MailException e) {
            log.info("ERROR: Message 전송 실패");
            throw e;
        }
    }

    private String changePassword(String email) {
        User user = userService.findUserByEmail(email);
        log.info("changePassword : " + user);
        String newPassword = randomString();
        user.setPassword(newPassword);
        userService.saveUser(user);
        return newPassword;
    }

    private String randomString() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(chars.length());
            code.append(chars.charAt(index));
        }

        return code.toString();
    }

    public String loadHtmlTemplate(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        byte[] bytes = Files.readAllBytes(resource.getFile().toPath());
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
