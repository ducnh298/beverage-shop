package com.drinkshop.services;

import com.drinkshop.dto.DrinkDTO;
import com.drinkshop.model.Drink;

import java.util.List;

public interface IDrinkService {
    List<DrinkDTO> findAll();

    DrinkDTO saveOrUpdate(Drink drink);
}
