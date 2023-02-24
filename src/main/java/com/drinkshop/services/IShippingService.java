package com.drinkshop.services;

import java.util.Map;

public interface IShippingService {

    Map<String, Object> getProvince();

    Map<String, Object> getDistrict(int provinceId);

    Map<String, Object> getWard(int districtId);

    Map<String, Object> getService(int fromDistrict, int toDistrict);
}
