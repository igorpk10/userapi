package com.igaopk.userapi.bases;

import com.igaopk.userapi.configurations.security.services.TokenService;
import com.igaopk.userapi.users.entitys.User;
import com.igaopk.userapi.users.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class BaseUserCredentials {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    protected UserServiceImpl userRepository;

    protected User validateUserCredencials(String username, String password, String credentials) {
        var subject = tokenService.getSubject(credentials);
        var user = userRepository.findByUserName(username);

        if (username.equals(subject) && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}
