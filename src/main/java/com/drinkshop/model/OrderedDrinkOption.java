package com.drinkshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ordered_drink_option", schema = "public")
@Data
public class OrderedDrinkOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ordered_drink_id")
    private OrderedDrink orderedDrink;

    @ManyToOne
    @JoinColumn(name = "drink_option_id")
    private DrinkOption drinkOption;

    @Column
    private int quantity = 1;
}
