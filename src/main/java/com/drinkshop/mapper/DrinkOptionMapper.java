package com.drinkshop.mapper;

import com.drinkshop.dto.DrinkOptionDTO;
import com.drinkshop.model.DrinkOption;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DrinkOptionMapper {
    @Autowired
    ModelMapper modelMapper;

    public List<DrinkOptionDTO> toDTOs(List<DrinkOption> list) {
        List<DrinkOptionDTO> result = new ArrayList<>();
        for (DrinkOption drinkOption : list) {
            result.add(modelMapper.map(drinkOption, DrinkOptionDTO.class));
        }
        return result;
    }
}
