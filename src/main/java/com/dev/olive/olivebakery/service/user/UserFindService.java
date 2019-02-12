package com.dev.olive.olivebakery.service.user;

import com.dev.olive.olivebakery.exception.UserDefineException;
import com.dev.olive.olivebakery.model.entity.User;
import com.dev.olive.olivebakery.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Created by YoungMan on 2019-02-09.
 */

@Service
public class UserFindService {

    private final UserRepository userRepository;

    public UserFindService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserDefineException("해당 Id의 유저가 없습니다."));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserDefineException("해당 이메일의 유저가 없습니다."));
    }

}
