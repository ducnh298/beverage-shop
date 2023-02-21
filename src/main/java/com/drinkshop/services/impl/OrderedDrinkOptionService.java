package com.drinkshop.services.impl;

import com.drinkshop.model.OrderedDrink;
import com.drinkshop.model.OrderedDrinkOption;
import com.drinkshop.repository.OrderedDrinkOptionRepository;
import com.drinkshop.services.IDrinkOptionService;
import com.drinkshop.services.IOrderedDrinkOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedDrinkOptionService implements IOrderedDrinkOptionService {
    @Autowired
    OrderedDrinkOptionRepository orderedDrinkOptionRepository;

    @Autowired
    IDrinkOptionService drinkOptionService;

    @Override
    public int save(OrderedDrinkOption orderedDrinkOption) {
        if (orderedDrinkOption.getId() == null || orderedDrinkOption.getId() <= 0) {
            orderedDrinkOption.setDrinkOption(drinkOptionService.findById(orderedDrinkOption.getDrinkOption().getId()));
            return orderedDrinkOptionRepository.save(orderedDrinkOption).getId();
        } else return 0;
    }

    @Override
    public int saveAll(List<OrderedDrinkOption> orderedDrinkOptionList, int orderedDrinkId) {
        int count = 0;
        OrderedDrink orderedDrink = new OrderedDrink();
        orderedDrink.setId(orderedDrinkId);
        for (OrderedDrinkOption orderedDrinkOption : orderedDrinkOptionList) {
            orderedDrinkOption.setOrderedDrink(orderedDrink);
            save(orderedDrinkOption);
            count++;
        }
        return count;
    }
}
