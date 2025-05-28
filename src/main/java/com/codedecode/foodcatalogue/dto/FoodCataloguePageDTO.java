package com.codedecode.foodcatalogue.dto;

import com.codedecode.foodcatalogue.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePageDTO {

    // todo - why not using the FoodItemDTO. The course video doesnt use it but why
    private List<FoodItem> foodItemList;
    private RestaurantDTO restaurant;
}
