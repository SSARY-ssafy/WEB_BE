package com.ssary.diary_web.service;

import com.ssary.diary_web.domain.User;
import com.ssary.diary_web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> login(String email, String password) {
        // 이메일을 기준으로 사용자를 조회
        Optional<User> optionalUser = userRepository.findByEmail(email);

        // 해당 하는 사용자가 없는 경우
        if(optionalUser.isEmpty()){
            return Optional.empty();
        }

        // 비밀번호가 일치하는지 확인
        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            return optionalUser;  // 로그인 성공
        }

        return Optional.empty();  // 로그인 실패
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean permitUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPermission((byte) 1); // 권한을 '허가' 상태로 변경
            userRepository.save(user); // 변경된 정보를 데이터베이스에 저장
            return true;
        }
        return false;
    }
}
