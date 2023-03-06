package com.drinkshop.services.impl;

import com.drinkshop.dto.OrderDTO;
import com.drinkshop.mapper.OrderMapper;
import com.drinkshop.model.*;
import com.drinkshop.repository.OrderRepository;
import com.drinkshop.services.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    IShippingService shippingService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderMapper orderMapper;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public List<OrderDTO> findAll() {
        List<Order> list = orderRepository.findAll();
        return orderMapper.toDTOs(list);
    }

    @Override
    public <T> T findById(Integer id, Class<T> classType) {
        return (T) modelMapper.map(orderRepository.findById(id, classType), classType);

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

            if (order.getDrinkList() != null && !order.getDrinkList().isEmpty())
                orderedDrinkService.saveAll(order.getDrinkList(), orderId);

            order = orderRepository.findById(orderId, Order.class);
            if (order.getOrderType().equals(EnumForEntity.OrderType.shipping) && orderExtraData != null) {
                order.setOrderExtraData(orderExtraData);

                Map<String, Object> responseCreateShippingOrder = shippingService.createOrder(order);

                if (responseCreateShippingOrder != null) {
                    orderExtraData.setShippingOrderCode((String) ((Map<String, Object>) responseCreateShippingOrder.get("data")).get("order_code"));
                    orderExtraData.setShippingCost(BigDecimal.valueOf(Long.valueOf(((Map<String, Object>) responseCreateShippingOrder.get("data")).get("total_fee").toString())));
                } else logger.error("Failed to create shipping order!");
                orderExtraData.setOrder(order);
                order.setOrderExtraData(orderExtraDataService.saveOrUpdate(orderExtraData));
            }

            order.setTotal(updateOrderTotal(orderId));
        } else {
            Order oldOrder = orderRepository.findById(order.getId()).orElse(null);
            order = orderMapper.mapExisting(order, oldOrder);
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
