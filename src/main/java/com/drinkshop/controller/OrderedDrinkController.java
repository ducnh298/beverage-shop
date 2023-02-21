package com.drinkshop.controller;

import com.drinkshop.model.OrderedDrink;
import com.drinkshop.services.IOrderedDrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ordered-drink")
public class OrderedDrinkController {
    @Autowired
    IOrderedDrinkService orderedDrinkService;

    @GetMapping
    public ResponseEntity<List<OrderedDrink>> getAll(){
        return new ResponseEntity<>(orderedDrinkService.findAll(), HttpStatus.OK);
    }
}
