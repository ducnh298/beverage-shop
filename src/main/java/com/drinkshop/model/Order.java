package com.drinkshop.model;

import com.drinkshop.model.EnumForEntity.OrderStatus;
import com.drinkshop.model.EnumForEntity.OrderType;
import com.drinkshop.model.EnumForEntity.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "order")
@NoArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderedDrink> listDrinks;

    @Column
    private OrderType orderType;

    @Column
    private OrderStatus orderStatus;

    @Column
    private BigDecimal total;

    @Column
    private BigDecimal shippingCost;

    @Column
    private PaymentType  paymentType;
}
