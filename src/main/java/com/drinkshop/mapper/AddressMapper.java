package com.drinkshop.mapper;

import com.drinkshop.dto.AddressDTO;
import com.drinkshop.model.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressMapper {
    @Autowired
    ModelMapper modelMapper;

    public List<AddressDTO> toDTOs(List<Address> list) {
        List<AddressDTO> result = new ArrayList<>();
        for (Address address : list) {
            result.add(modelMapper.map(address, AddressDTO.class));
        }
        return result;
    }
}
