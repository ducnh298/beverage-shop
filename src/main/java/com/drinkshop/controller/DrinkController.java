package com.drinkshop.controller;

import com.drinkshop.dto.DrinkDTO;
import com.drinkshop.model.Drink;
import com.drinkshop.services.IDrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drink")
public class DrinkController {
    @Autowired
    IDrinkService drinkService;

    @GetMapping
    public ResponseEntity<List<DrinkDTO>> getAll(){
        return new ResponseEntity<>(drinkService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DrinkDTO> createDrink(@RequestBody Drink Drink){
        return  new ResponseEntity<>(drinkService.saveOrUpdate(Drink),HttpStatus.OK);
    }
}
