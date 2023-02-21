package com.drinkshop.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
public class Drink extends BaseEntity {

    @Column
    private String name;
    @Column
    private BigDecimal price;
    @Column
    private String description;
}
