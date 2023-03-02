package com.drinkshop.services.impl;

import com.drinkshop.dto.UserDTO;
import com.drinkshop.mapper.UserMapper;
import com.drinkshop.model.User;
import com.drinkshop.repository.UserRepository;
import com.drinkshop.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> findAll() {
        return userMapper.toDTOs(userRepository.findAll());
    }

    @Override
    public UserDTO findByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username), UserDTO.class);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDTO saveOrUpdate(User user) {
        if (user.getId() != null && user.getId() > 0) {
            User oldUser = userRepository.findById(user.getId());
            user = userMapper.mapExisting(user, oldUser);
        }
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }
}
