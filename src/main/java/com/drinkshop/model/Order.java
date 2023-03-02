package com.drinkshop.model;

import com.drinkshop.model.EnumForEntity.OrderStatus;
import com.drinkshop.model.EnumForEntity.OrderType;
import com.drinkshop.model.EnumForEntity.PaymentType;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "orders", schema = "public")
@Data
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "cashier_id")
    private User cashier;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderedDrink> drinkList;

    @OneToOne(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
    private OrderExtraData orderExtraData;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private OrderType orderType = OrderType.takeAway;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus = OrderStatus.processed;

    @Column
    private BigDecimal total = BigDecimal.valueOf(0);

    @Column
    @Enumerated(EnumType.ORDINAL)
    private PaymentType paymentType = PaymentType.cash;

    @Column
    private Boolean paid = false;

    @Column
    private String note;
}
