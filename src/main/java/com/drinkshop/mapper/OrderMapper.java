package com.drinkshop.mapper;

import com.drinkshop.dto.OrderDTO;
import com.drinkshop.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
    @Autowired
    ModelMapper modelMapper;

    public List<OrderDTO> toDTOs(List<Order> list) {
        List<OrderDTO> result = new ArrayList<>();
        for (Order order : list) {
            result.add(modelMapper.map(order, OrderDTO.class));
        }
        return result;
    }

    public Order mapExisting(Order order, Order oldOrder){
        if(order.getCustomer()!=null)
            oldOrder.setCustomer(order.getCustomer());
        if(order.getCashier()!=null)
            oldOrder.setCashier(order.getCashier());
        if(order.getOrderType()!=null)
            oldOrder.setOrderType(order.getOrderType());
        if(order.getOrderStatus()!=null)
            oldOrder.setOrderStatus(order.getOrderStatus());
        if(order.getTotal()!=null)
            oldOrder.setTotal(order.getTotal());
        if(order.getPaymentType()!=null)
            oldOrder.setPaymentType(order.getPaymentType());
        if(order.getPaid()!=null)
            oldOrder.setPaid(order.getPaid());
        if(order.getNote()!=null)
            oldOrder.setNote(order.getNote());
        return oldOrder;
    }
}
