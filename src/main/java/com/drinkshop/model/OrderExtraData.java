package com.drinkshop.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_extra_data", schema = "public")
@Data
public class OrderExtraData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column
    private BigDecimal shippingCost = BigDecimal.valueOf(0);

    @Column
    private int wardCode;

    @Column
    private int districtId;

    @Column
    private String addressLine;

    @Column
    private String ward;

    @Column
    private String district;

    @Column
    private String province;
}
