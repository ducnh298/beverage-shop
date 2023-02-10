package com.drinkshop.services.impl;

import com.drinkshop.model.User;
import com.drinkshop.repository.UserRepository;
import com.drinkshop.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }
}
