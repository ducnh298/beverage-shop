package com.drinkshop.services;

import com.drinkshop.model.OrderedDrinkOption;

import java.util.List;

public interface IOrderedDrinkOptionService{
    int save(OrderedDrinkOption orderedDrinkOption);

    int saveAll(List<OrderedDrinkOption> orderedDrinkOptionList, int orderedDrinkId);


}
