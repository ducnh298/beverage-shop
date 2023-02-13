package com.drinkshop.repository;

import com.drinkshop.dto.DrinkOptionDTO;
import com.drinkshop.model.DrinkOption;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkOptionRepository extends CrudRepository<DrinkOption, Integer> {

    List<DrinkOption> findAll();

    DrinkOption save(DrinkOption drinkOption);
}
