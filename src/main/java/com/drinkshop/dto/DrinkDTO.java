package com.drinkshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class DrinkDTO extends  BaseDTO {

    private String name;

    private BigDecimal price;

    private String description;
}
