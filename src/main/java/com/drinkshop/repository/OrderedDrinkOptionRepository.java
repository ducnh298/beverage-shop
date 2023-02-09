package com.drinkshop.repository;

import com.drinkshop.model.OrderedDrinkOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedDrinkOptionRepository extends JpaRepository<OrderedDrinkOption,Integer> {
}
