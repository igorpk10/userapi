package com.igaopk.userapi.users.services;

import com.igaopk.userapi.users.entitys.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User save(User user);

    public User findByUserName(String username);
}
