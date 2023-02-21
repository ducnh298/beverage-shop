package com.drinkshop.services;

import com.drinkshop.dto.UserDTO;
import com.drinkshop.model.User;

import java.util.List;

public interface IUserService {
     List<UserDTO> findAll();
     UserDTO findByUsername(String username);

     User findById(int id);

     UserDTO saveOrUpdate(User user);
}
