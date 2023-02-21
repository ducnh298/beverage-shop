package com.drinkshop.repository;

import com.drinkshop.model.OrderedDrinkOption;
import org.springframework.data.repository.CrudRepository;

public interface OrderedDrinkOptionRepository extends CrudRepository<OrderedDrinkOption,Integer> {
    OrderedDrinkOption save(OrderedDrinkOption orderedDrinkOption);
}
