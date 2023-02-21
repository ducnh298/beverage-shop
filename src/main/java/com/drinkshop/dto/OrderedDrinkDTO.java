package com.drinkshop.dto;

import com.drinkshop.model.EnumForEntity;
import lombok.Data;

import java.util.List;

@Data
public class OrderedDrinkDTO {
    private DrinkDTO drink;

    private List<OrderedDrinkOptionDTO> orderedDrinkOptionList;

    private EnumForEntity.DrinkSize drinkSize;

    private int quantity;

    private int percentIce;

    private int percentSugar;
}
