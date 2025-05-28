package com.codedecode.foodcatalogue.service;

import com.codedecode.foodcatalogue.dto.FoodCataloguePageDTO;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.dto.RestaurantDTO;
import com.codedecode.foodcatalogue.entity.FoodItem;
import com.codedecode.foodcatalogue.mapper.FoodItemMapper;
import com.codedecode.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO saveFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem mappedFoodItem = FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO);
        FoodItem savedFoodItem = foodItemRepo.save(mappedFoodItem);
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(savedFoodItem);
    }

    public FoodCataloguePageDTO fetchFoodCataloguePageDetailsByRestaurantId(int restaurantId) {
        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        RestaurantDTO restaurantDTO = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePageDTO(foodItemList, restaurantDTO);
    }

    private FoodCataloguePageDTO createFoodCataloguePageDTO(List<FoodItem> foodItemList, RestaurantDTO restaurantDTO) {
        return new FoodCataloguePageDTO(foodItemList, restaurantDTO); // todo different to the course video. there we are using setters
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId); // todo im still not sure how jpa does this, gets extended with customized methods
    }

    private RestaurantDTO fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, RestaurantDTO.class);
    }
}
