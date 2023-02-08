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

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "cashier_id", nullable = false)
    private User cashier;

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

    @Column
    private Boolean isPaid;

    @Column
    private String note;
}
