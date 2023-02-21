package com.drinkshop.services;

import com.drinkshop.dto.OrderDTO;
import com.drinkshop.model.Order;

import java.math.BigDecimal;
import java.util.List;

public interface IOrderService {
    List<OrderDTO> findAll();

    OrderDTO findById(Integer id);

    OrderDTO saveOrUpdate(Order order);

    BigDecimal updateOrderTotal(int orderId);
}
