package com.drinkshop.dto;

import com.drinkshop.model.EnumForEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO extends BaseDTO {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private UserDTO customer;

    private String customerName;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private UserDTO cashier;

    private String cashierName;

    private List<OrderedDrinkDTO> drinkList;

    private EnumForEntity.OrderType orderType;

    private EnumForEntity.OrderStatus orderStatus;

    private BigDecimal total;

    private BigDecimal shippingCost;

    private EnumForEntity.PaymentType paymentType;

    private Boolean isPaid;

    private String note;

    public void setCustomer(UserDTO customer) {
        this.customer = customer;
        this.customerName = this.customer.getFullName();
    }

    public void setCashier(UserDTO cashier) {
        this.cashier = cashier;
        this.cashierName = this.cashier.getFullName();
    }
}
