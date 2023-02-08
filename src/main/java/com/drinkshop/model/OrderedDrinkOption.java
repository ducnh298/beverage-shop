package com.drinkshop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ordered_drink_option", schema = "public")
@NoArgsConstructor
@Getter
@Setter
public class OrderedDrinkOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ordered_drink_id")
    private OrderedDrink orderedDrink;

    @ManyToOne
    @JoinColumn(name = "drink_option_id")
    private DrinkOption drinkOption;
}
