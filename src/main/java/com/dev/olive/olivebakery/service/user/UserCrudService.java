package com.dev.olive.olivebakery.service.user;

import com.dev.olive.olivebakery.model.entity.User;
import com.dev.olive.olivebakery.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Created by YoungMan on 2019-02-12.
 */

@Service
public class UserCrudService {

    private final UserRepository userRepository;
    private UserFindService userFindService;

    public UserCrudService(UserRepository userRepository, UserFindService userFindService) {
        this.userRepository = userRepository;
        this.userFindService = userFindService;
    }

    public void deleteUser(String userId) {
        User user = userFindService.findById(userId);
        userRepository.delete(user);
    }
}
