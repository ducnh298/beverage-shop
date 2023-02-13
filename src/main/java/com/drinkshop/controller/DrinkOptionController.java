package com.drinkshop.controller;

import com.drinkshop.dto.DrinkOptionDTO;
import com.drinkshop.model.DrinkOption;
import com.drinkshop.services.IDrinkOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drink-option")
public class DrinkOptionController {

    @Autowired
    IDrinkOptionService drinkOptionService;

    @GetMapping
    public ResponseEntity<List<DrinkOptionDTO>> getAll(){
        return new ResponseEntity<>(drinkOptionService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DrinkOptionDTO> create(@RequestBody DrinkOption drinkOption){
        drinkOption.setId(null);
        return new ResponseEntity<>(drinkOptionService.saveOrUpdate(drinkOption),HttpStatus.OK);
    }
}
