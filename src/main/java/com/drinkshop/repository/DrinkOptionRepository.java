package com.drinkshop.repository;

import com.drinkshop.model.DrinkOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrinkOptionRepository extends JpaRepository<DrinkOption,Integer> {
}
