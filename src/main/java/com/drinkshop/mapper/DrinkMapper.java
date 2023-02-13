package com.drinkshop.mapper;

import com.drinkshop.dto.DrinkDTO;
import com.drinkshop.model.Drink;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DrinkMapper {
    @Autowired
    ModelMapper modelMapper;

    public List<DrinkDTO> toDTOs(List<Drink> list){
        List<DrinkDTO> result = new ArrayList<>();
        for(Drink drink:list){
            result.add(modelMapper.map(drink,DrinkDTO.class));
        }
        return result;
    }
}
