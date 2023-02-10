package com.drinkshop.services;

import com.drinkshop.model.User;

import java.util.List;

public interface IUserService {
     List<User> findAll();
     User findByUsername(String username);

     User saveOrUpdate(User user);
}
