package com.drinkshop.mapper;

import com.drinkshop.dto.UserDTO;
import com.drinkshop.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    @Autowired
    ModelMapper modelMapper;

    public List<UserDTO> toDTOs(List<User> list) {
        List<UserDTO> result = new ArrayList<>();
        for (User user : list) {
            result.add(modelMapper.map(user, UserDTO.class));
        }
        return result;
    }

    public User mapExisting(User user, User oldUser) {
        if (user.getUsername() != null) {
            oldUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            oldUser.setPassword(user.getPassword());
        }
        if (user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        if (user.getFullName() != null) {
            oldUser.setFullName(user.getFullName());
        }
        if (user.getPhone() != null) {
            oldUser.setPhone(user.getPhone());
        }
        if (user.getDateOfBirth() != null) {
            oldUser.setDateOfBirth(user.getDateOfBirth());
        }
        if (user.getUserType() != null) {
            oldUser.setUserType(user.getUserType());
        }
        return oldUser;
    }
}
