package com.drinkshop.services.impl;

import com.drinkshop.model.Order;
import com.drinkshop.model.OrderedDrink;
import com.drinkshop.repository.OrderedDrinkRepository;
import com.drinkshop.services.IDrinkService;
import com.drinkshop.services.IOrderedDrinkOptionService;
import com.drinkshop.services.IOrderedDrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedDrinkService implements IOrderedDrinkService {

    @Autowired
    OrderedDrinkRepository orderedDrinkRepository;

    @Autowired
    IOrderedDrinkOptionService orderedDrinkOptionService;

    @Autowired
    IDrinkService drinkService;

    @Override
    public List<OrderedDrink> findAll() {
        return orderedDrinkRepository.findAll();
    }

    @Override
    public int save(OrderedDrink orderedDrink) {
        if (orderedDrink.getId() == null || orderedDrink.getId() <= 0) {
            orderedDrink.setDrink(drinkService.findById(orderedDrink.getDrink().getId()));
            int orderedDrinkId = orderedDrinkRepository.save(orderedDrink).getId();

            if (orderedDrink.getOrderedDrinkOptionList() != null && !orderedDrink.getOrderedDrinkOptionList().isEmpty()) {
                orderedDrinkOptionService.saveAll(orderedDrink.getOrderedDrinkOptionList(), orderedDrinkId);
            }
            return orderedDrinkId;
        } else return 0;
    }

    @Override
    public int saveAll(List<OrderedDrink> drinkList, int orderId) {
        int count = 0;
        Order order = new Order();
        order.setId(orderId);
        for (OrderedDrink orderedDrink : drinkList) {
            orderedDrink.setOrder(order);
            save(orderedDrink);
            count++;
        }
        return count;
    }
}
