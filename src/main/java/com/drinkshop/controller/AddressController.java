package com.drinkshop.controller;

import com.drinkshop.services.IAddressService;
import com.drinkshop.services.IGHNService;
import com.drinkshop.services.impl.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    IAddressService addressService;

    @Autowired
    IGHNService ghnService;

    @GetMapping("/province")
    public ResponseEntity<Map<String, Object>> getProvince() {
        return new ResponseEntity<>(ghnService.getProvince(), HttpStatus.OK);
    }

    @GetMapping("/district")
    public ResponseEntity<Map<String, Object>> getDistrict(@Param("provinceId") int provinceId) {
        return new ResponseEntity<>(ghnService.getDistrict(provinceId), HttpStatus.OK);
    }

    @GetMapping("/ward")
    public ResponseEntity<Map<String, Object>> getWard(@Param("districtId") int districtId) {
        return new ResponseEntity<>(ghnService.getWard(districtId), HttpStatus.OK);
    }
}
