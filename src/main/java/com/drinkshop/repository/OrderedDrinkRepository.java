package com.drinkshop.repository;

import com.drinkshop.model.OrderedDrink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedDrinkRepository extends JpaRepository<OrderedDrink,Integer> {
}
