package com.drinkshop.controller;

import com.drinkshop.dto.OrderDTO;
import com.drinkshop.model.Order;
import com.drinkshop.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    IOrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOne(@PathVariable int id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.saveOrUpdate(order), HttpStatus.OK);
    }

    @PutMapping("/{id}/total")
    public ResponseEntity<BigDecimal> updateOrderTotal(@PathVariable(name = "id") int orderId) {
        return new ResponseEntity<>(orderService.updateOrderTotal(orderId), HttpStatus.OK);
    }
}
