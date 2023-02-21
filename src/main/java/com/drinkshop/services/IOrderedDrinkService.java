package com.drinkshop.services;

import com.drinkshop.model.OrderedDrink;

import java.util.List;

public interface IOrderedDrinkService{

    List<OrderedDrink> findAll();
    int save(OrderedDrink orderedDrink);

    int saveAll(List<OrderedDrink> drinkList, int orderId);
}
