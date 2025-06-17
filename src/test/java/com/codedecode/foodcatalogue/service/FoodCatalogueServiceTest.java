package com.codedecode.foodcatalogue.service;

import com.codedecode.foodcatalogue.dto.FoodCataloguePageDTO;
import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.dto.RestaurantDTO;
import com.codedecode.foodcatalogue.entity.FoodItem;
import com.codedecode.foodcatalogue.mapper.FoodItemMapper;
import com.codedecode.foodcatalogue.repo.FoodItemRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class FoodCatalogueServiceTest {

    @Mock
    private FoodItemRepo foodItemRepo;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FoodCatalogueService foodCatalogueService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addFoodItem_ShouldSaveFoodItemAndReturnMappedDTO() {
        // Arrange
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        FoodItem foodItem = new FoodItem();
        when(foodItemRepo.save(any(FoodItem.class))).thenReturn(foodItem);

        // Act
        FoodItemDTO result = foodCatalogueService.saveFoodItem(foodItemDTO);

        // Assert
        verify(foodItemRepo, times(1)).save(any(FoodItem.class));
        Assertions.assertEquals(FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItem), result);
    }

    @Test
    void fetchFoodCataloguePageDetails_ShouldReturnFoodCataloguePage() {
        // Arrange
        int restaurantId = 123;
        List<FoodItem> foodItemList = Arrays.asList(new FoodItem());
        RestaurantDTO restaurant = new RestaurantDTO();
        when(foodItemRepo.findByRestaurantId(restaurantId)).thenReturn(foodItemList);
        when(restTemplate.getForObject(anyString(), eq(RestaurantDTO.class))).thenReturn(restaurant);

        // Act
        FoodCataloguePageDTO result = foodCatalogueService.fetchFoodCataloguePageDetailsByRestaurantId(restaurantId);

        // Assert
        verify(foodItemRepo, times(1)).findByRestaurantId(restaurantId);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(RestaurantDTO.class));
        Assertions.assertEquals(foodItemList, result.getFoodItemList());
        Assertions.assertEquals(restaurant, result.getRestaurant());
    }
}
