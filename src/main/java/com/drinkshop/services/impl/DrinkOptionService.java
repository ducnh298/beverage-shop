package com.drinkshop.services.impl;

import com.drinkshop.dto.DrinkOptionDTO;
import com.drinkshop.mapper.DrinkMapper;
import com.drinkshop.mapper.DrinkOptionMapper;
import com.drinkshop.model.DrinkOption;
import com.drinkshop.repository.DrinkOptionRepository;
import com.drinkshop.services.IDrinkOptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkOptionService implements IDrinkOptionService {

    @Autowired
    DrinkOptionRepository drinkOptionRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DrinkOptionMapper drinkOptionMapper;
    @Override
    public List<DrinkOptionDTO> findAll() {
        return drinkOptionMapper.toDTOs(drinkOptionRepository.findAll());
    }

    @Override
    public DrinkOptionDTO saveOrUpdate(DrinkOption drinkOption) {
        return modelMapper.map(drinkOptionRepository.save(drinkOption),DrinkOptionDTO.class);
    }
}
