package com.drinkshop.services;

import com.drinkshop.dto.OrderForCalculatingShippingCost;
import com.drinkshop.model.Order;

import java.util.Map;

public interface IShippingService {

    Map<String, Object> getProvince();

    Map<String, Object> getDistrict(int provinceId);

    Map<String, Object> getWard(int districtId);

    Map<String, Object> getService(int fromDistrict, int toDistrict);

    Map<String, Object> calculateCost(OrderForCalculatingShippingCost order);

    Map<String, Object> createOrder(Order order);

}
