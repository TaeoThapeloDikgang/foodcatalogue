package com.codedecode.foodcatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {

    private int id;
    private String itemName;
    private String itemDescription;
    private Boolean isVeg;
    private long price;
    private int restaurantId;
    private int quantity;

}
