package com.drinkshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "drink")
@NoArgsConstructor
@Getter
@Setter
public class Drink extends BaseEntity {

    @Column
    private String name;
    @Column
    private BigDecimal price;
    @Column
    private String description;
}
