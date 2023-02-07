package com.drinkshop.model;

import liquibase.pro.packaged.J;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "ordered_drink")
@NoArgsConstructor
@Getter
@Setter
public class OrderedDrink extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @Column
    private int quantity;

    @Column
    private int percentIce;

    @Column
    private int percentSugar;
}
