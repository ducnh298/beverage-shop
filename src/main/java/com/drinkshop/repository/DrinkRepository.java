package com.drinkshop.repository;

import com.drinkshop.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink,Integer> {
    List<Drink> findAll();
}
