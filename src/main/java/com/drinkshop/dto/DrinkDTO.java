package com.drinkshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DrinkDTO extends BaseDTO {

    private String name;

    private BigDecimal price;

    private String description;
}
