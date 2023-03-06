package com.drinkshop.services;

import com.drinkshop.dto.OrderDTO;
import com.drinkshop.model.Order;

import java.math.BigDecimal;
import java.util.List;

public interface IOrderService {
    List<OrderDTO> findAll();

    <T> T findById(Integer id, Class<T> classType);

    OrderDTO saveOrUpdate(Order order);

    BigDecimal updateOrderTotal(int orderId);
}
