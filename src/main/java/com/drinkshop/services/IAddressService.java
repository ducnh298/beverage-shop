package com.drinkshop.services;

import com.drinkshop.dto.AddressDTO;
import com.drinkshop.model.Address;

import java.util.List;

public interface IAddressService {
    List<AddressDTO> findByUserId(int userId);

    AddressDTO findById(int id);

    Integer saveOrUpdate(Address address);
}
