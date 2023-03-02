package com.drinkshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_extra_data", schema = "public")
@Data
public class OrderExtraData {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column
    private BigDecimal shippingCost = BigDecimal.valueOf(0);

    @Column
    private Integer serviceId;

    @Column
    private int serviceTypeId = 2;

    @Column
    private String wardCode;

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
