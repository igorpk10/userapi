package com.igaopk.userapi.users.services;

import com.igaopk.userapi.users.entitys.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public void save(User user);

    public User findByUserName(String username);

    public List<User> findAll();
}
