package com.codedecode.foodcatalogue.controller;

import com.codedecode.foodcatalogue.dto.FoodCataloguePageDTO;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {

    @Autowired
    FoodCatalogueService foodCatalogueService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO savedFoodItemDTO = foodCatalogueService.saveFoodItem(foodItemDTO);
        return new ResponseEntity<>(savedFoodItemDTO, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePageDTO> fetchRestaurantAndFoodItemsById(@PathVariable int restaurantId) {
        FoodCataloguePageDTO fetchedFoodCataloguePageDTO = foodCatalogueService.fetchFoodCataloguePageDetailsByRestaurantId(restaurantId);
        return new ResponseEntity<>(fetchedFoodCataloguePageDTO, HttpStatus.OK);
    }
}
