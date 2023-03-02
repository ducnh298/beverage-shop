package com.drinkshop.services.impl;

import com.drinkshop.dto.DrinkDTO;
import com.drinkshop.mapper.DrinkMapper;
import com.drinkshop.model.Drink;
import com.drinkshop.repository.DrinkRepository;
import com.drinkshop.services.IDrinkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkService implements IDrinkService {

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DrinkMapper drinkMapper;

    @Override
    public List<DrinkDTO> findAll() {
        return drinkMapper.toDTOs(drinkRepository.findAll());
    }

    @Override
    public Drink findById(int id) {
        return drinkRepository.findById(id);
    }

    @Override
    public DrinkDTO saveOrUpdate(Drink drink) {
        if (drink.getId() != null && drink.getId() > 0) {
            Drink oldDrink = drinkRepository.findById(drink.getId()).orElse(null);
            drink = drinkMapper.mapExisting(drink, oldDrink);
        }
        return modelMapper.map(drinkRepository.save(drink), DrinkDTO.class);
    }

}
