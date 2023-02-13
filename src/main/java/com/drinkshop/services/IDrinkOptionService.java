package com.drinkshop.services;


import com.drinkshop.dto.DrinkOptionDTO;
import com.drinkshop.model.DrinkOption;

import java.util.List;

public interface IDrinkOptionService{
    List<DrinkOptionDTO> findAll();

    DrinkOptionDTO saveOrUpdate(DrinkOption drinkOption);
}
