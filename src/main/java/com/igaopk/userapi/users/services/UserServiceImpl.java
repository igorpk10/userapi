package com.igaopk.userapi.users.services;

import com.igaopk.userapi.configurations.exceptions.UserNameDuplicateException;
import com.igaopk.userapi.users.entitys.User;
import com.igaopk.userapi.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public void save(User user) {
        try {
            repository.save(user);
        } catch (DataIntegrityViolationException ex) {
            System.out.println(ex.getMessage());
            throw new UserNameDuplicateException();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public User findByUserName(String username) {
        try {
            return (User) repository.findByUserName(username);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
    }

    @Override
    public List<User> findAll() {
        return repository.retrieveAll();
    }
}
