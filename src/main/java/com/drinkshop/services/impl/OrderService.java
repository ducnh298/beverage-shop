package com.drinkshop.services.impl;

import com.drinkshop.dto.OrderDTO;
import com.drinkshop.mapper.OrderMapper;
import com.drinkshop.model.*;
import com.drinkshop.repository.OrderRepository;
import com.drinkshop.services.IOrderExtraDataService;
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
    IOrderExtraDataService orderExtraDataService;

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

            if (order.getCustomer() != null)
                order.setCustomer(userService.findById(order.getCustomer().getId()));
            if (order.getCashier() != null)
                order.setCashier(userService.findById(order.getCashier().getId()));

            OrderExtraData orderExtraData = order.getOrderExtraData();
            order.setOrderExtraData(null);

            int orderId = orderRepository.save(order).getId();

            if (order.getOrderType().equals(EnumForEntity.OrderType.shipping) && orderExtraData != null) {
                orderExtraData.setOrder(order);
                order.setOrderExtraData(orderExtraDataService.saveOrUpdate(orderExtraData));
            }

            if (order.getDrinkList() != null && !order.getDrinkList().isEmpty())
                orderedDrinkService.saveAll(order.getDrinkList(), orderId);

            order.setTotal(updateOrderTotal(orderId));
        } else {
            Order oldOrder = orderRepository.findById(order.getId()).orElse(null);
            order = orderMapper.mapExisting(order,oldOrder);
        }
        return modelMapper.map(order, OrderDTO.class);
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
        if (order.getOrderType().equals(EnumForEntity.OrderType.shipping) && order.getOrderExtraData() != null)
            orderTotal = orderTotal.add(order.getOrderExtraData().getShippingCost());

        orderRepository.updateOrderTotal(orderTotal, order.getId());
        return orderTotal;
    }
}
