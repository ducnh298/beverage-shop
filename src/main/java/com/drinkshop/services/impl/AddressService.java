package com.drinkshop.services.impl;

import com.drinkshop.dto.AddressDTO;
import com.drinkshop.mapper.AddressMapper;
import com.drinkshop.model.Address;
import com.drinkshop.repository.AddressRepository;
import com.drinkshop.services.IAddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<AddressDTO> findByUserId(int userId) {
        return addressMapper.toDTOs(addressRepository.findByUserId(userId));
    }

    @Override
    public AddressDTO findById(int id) {
        return modelMapper.map(addressRepository.findById(id), AddressDTO.class);
    }

    @Override
    public Integer saveOrUpdate(Address address) {
        if (address.getId() == null || address.getId() <= 0)
            return addressRepository.save(address).getId();
        return null;
    }
}
