package com.drinkshop.services.impl;

import com.drinkshop.dto.OrderDTO;
import com.drinkshop.mapper.OrderMapper;
import com.drinkshop.model.Order;
import com.drinkshop.model.OrderedDrink;
import com.drinkshop.model.OrderedDrinkOption;
import com.drinkshop.repository.OrderRepository;
import com.drinkshop.services.IOrderService;
import com.drinkshop.services.IOrderedDrinkService;
import com.drinkshop.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    IOrderedDrinkService orderedDrinkService;

    @Autowired
    IUserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderMapper orderMapper;


    @Override
    public List<OrderDTO> findAll() {
        List<Order> list = orderRepository.findAll();
        return orderMapper.toDTOs(list);
    }

    @Override
    public OrderDTO findById(Integer id) {
        return modelMapper.map(orderRepository.findById(id).orElse(null), OrderDTO.class);
    }

    @Override
    public OrderDTO saveOrUpdate(Order order) {
        if (order.getId() == null || order.getId() <= 0) {
            order.setCustomer(userService.findById(order.getCustomer().getId()));
            order.setCashier(userService.findById(order.getCashier().getId()));
            int orderId = orderRepository.save(order).getId();
            if (order.getDrinkList() != null && order.getDrinkList().isEmpty())
                orderedDrinkService.saveAll(order.getDrinkList(), orderId);
            order.setTotal(updateOrderTotal(orderId));
            return modelMapper.map(order,OrderDTO.class);
        } else return null;
    }

    @Override
    public BigDecimal updateOrderTotal(int orderId) {
        Order order = orderRepository.findById(orderId).orElse(new Order());
        BigDecimal orderTotal = new BigDecimal(0);
        for (OrderedDrink orderedDrink : order.getDrinkList()) {
            if (orderedDrink != null && orderedDrink.getDrink() != null) {
                orderTotal = orderTotal.add(orderedDrink.getDrink().getPrice()
                        .multiply(BigDecimal.valueOf((orderedDrink.getDrinkSize().getValue() * 0.3) + 1))
                        .multiply(BigDecimal.valueOf(orderedDrink.getQuantity())));

                for (OrderedDrinkOption orderedDrinkOption : orderedDrink.getOrderedDrinkOptionList()) {
                    orderTotal = orderTotal.add(orderedDrinkOption.getDrinkOption().getPrice().multiply(BigDecimal.valueOf(orderedDrinkOption.getQuantity())));
                }
            }
        }
        orderRepository.updateOrderTotal(orderTotal, order.getId());
        return orderTotal;
    }
}
