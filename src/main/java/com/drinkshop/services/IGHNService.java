package com.drinkshop.services;

import java.util.Map;

public interface IGHNService {

    Map<String,Object> getProvince();

    Map<String,Object> getDistrict(int provinceId);

    Map<String,Object> getWard(int districtId);
}
