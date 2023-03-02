package com.drinkshop.services;

import com.drinkshop.dto.AddressDTO;
import com.drinkshop.model.OrderExtraData;

public interface IOrderExtraDataService {

    AddressDTO findById(int id);

    OrderExtraData saveOrUpdate(OrderExtraData address);
}
