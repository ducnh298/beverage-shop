package com.drinkshop.services.impl;

import com.drinkshop.dto.DrinkOptionDTO;
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
    public DrinkOption findById(int id) {
        return drinkOptionRepository.findById(id);
    }

    @Override
    public DrinkOptionDTO saveOrUpdate(DrinkOption drinkOption) {
        if (drinkOption.getId() != null && drinkOption.getId() > 0) {
            DrinkOption oldDrinkOption = drinkOptionRepository.findById(drinkOption.getId()).orElse(null);
            drinkOption = drinkOptionMapper.mapExisting(drinkOption, oldDrinkOption);
        }
        return modelMapper.map(drinkOptionRepository.save(drinkOption), DrinkOptionDTO.class);
    }
}
