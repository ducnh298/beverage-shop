package com.drinkshop.mapper;

import com.drinkshop.dto.UserDTO;
import com.drinkshop.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    @Autowired
    ModelMapper modelMapper;

    public List<UserDTO> toDTOs(List<User> list){
        List<UserDTO> result = new ArrayList<>();
        for(User user:list){
            result.add(modelMapper.map(user,UserDTO.class));
        }
        return result;
    }
}
