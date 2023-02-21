package com.drinkshop.repository;

import com.drinkshop.model.OrderedDrink;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderedDrinkRepository extends CrudRepository<OrderedDrink,Integer> {
    List<OrderedDrink> findAll();

    OrderedDrink save(OrderedDrink orderedDrink);
}
