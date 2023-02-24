package com.drinkshop.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(schema = "public")
@Data
public class DrinkOption extends BaseEntity {
    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private String description;
}
