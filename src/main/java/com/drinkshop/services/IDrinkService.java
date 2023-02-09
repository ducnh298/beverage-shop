package com.drinkshop.services;

import com.drinkshop.model.Drink;

import java.util.List;

public interface IDrinkService {
    List<Drink> findAll();
}
