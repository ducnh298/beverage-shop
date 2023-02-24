package com.drinkshop.controller;

import com.drinkshop.services.IOrderExtraDataService;
import com.drinkshop.services.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/address")
public class OrderExtraDataController {

    @Autowired
    IOrderExtraDataService addressService;

    @Autowired
    IShippingService shippingService;

    @GetMapping("/province")
    public ResponseEntity<Map<String, Object>> getProvince() {
        return new ResponseEntity<>(shippingService.getProvince(), HttpStatus.OK);
    }

    @GetMapping("/district")
    public ResponseEntity<Map<String, Object>> getDistrict(@RequestBody int provinceId) {
        return new ResponseEntity<>(shippingService.getDistrict(provinceId), HttpStatus.OK);
    }

    @GetMapping("/ward")
    public ResponseEntity<Map<String, Object>> getWard(@RequestBody int districtId) {
        return new ResponseEntity<>(shippingService.getWard(districtId), HttpStatus.OK);
    }
}
