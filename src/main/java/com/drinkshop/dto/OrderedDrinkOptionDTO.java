package com.drinkshop.dto;

import lombok.Data;

@Data
public class OrderedDrinkOptionDTO {
    private DrinkOptionDTO drinkOption;

    private int quantity;
}
