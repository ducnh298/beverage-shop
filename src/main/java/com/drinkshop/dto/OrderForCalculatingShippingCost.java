package com.drinkshop.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderForCalculatingShippingCost {
    private Integer serviceId;
    private int districtId;
    private String wardCode;
    private int numberOfDrinks;
    private BigDecimal totalDrinksCost;
}
