package com.drinkshop.controller;

import com.drinkshop.dto.OrderForCalculatingShippingCost;
import com.drinkshop.model.Order;
import com.drinkshop.services.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

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

    @GetMapping("/service")
    public ResponseEntity<Map<String, Object>> getService(@RequestBody Map<String, Integer> requestBody) {
        return new ResponseEntity<>(shippingService.getService(requestBody.get("fromDistrict"), requestBody.get("toDistrict")), HttpStatus.OK);
    }

    @GetMapping("/calculate-cost")
    public ResponseEntity<Map<String, Object>> calculateCost(@RequestBody OrderForCalculatingShippingCost order) {
        return new ResponseEntity<>(shippingService.calculateCost(order), HttpStatus.OK);
    }

    @PostMapping("/create-order")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(shippingService.createOrder(order), HttpStatus.OK);
    }
}
