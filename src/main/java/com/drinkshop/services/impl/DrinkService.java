package com.drinkshop.services.impl;

import com.drinkshop.model.Drink;
import com.drinkshop.services.IDrinkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkService implements IDrinkService{

    @Override
    public List<Drink> findAll() {
        return null;
    }
}
