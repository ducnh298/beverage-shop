package com.drinkshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "ordered_drink")
@NoArgsConstructor
@Getter
@Setter
public class OrderedDrink extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @OneToMany(mappedBy = "orderedDrink", fetch = FetchType.EAGER)
    private List<OrderedDrinkOption> orderedDrinkOptionList;

    @Column
    private int quantity;

    @Column
    private int percentIce;

    @Column
    private int percentSugar;
}
