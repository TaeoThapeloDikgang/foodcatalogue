package com.codedecode.foodcatalogue.controller;

import com.codedecode.foodcatalogue.dto.FoodItemDTO;
import com.codedecode.foodcatalogue.dto.FoodCataloguePageDTO;
import com.codedecode.foodcatalogue.service.FoodCatalogueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.mockito.Mockito.*;

public class FoodCatalogueControllerTest {

    @Mock
    private FoodCatalogueService foodCatalogueService;

    @InjectMocks
    private FoodCatalogueController foodCatalogueController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addFoodItem_ShouldReturnCreatedStatus() {
        // Arrange
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        when(foodCatalogueService.saveFoodItem(any(FoodItemDTO.class))).thenReturn(foodItemDTO);

        // Act
        ResponseEntity<FoodItemDTO> response = foodCatalogueController.addFoodItem(foodItemDTO);

        // Assert
        verify(foodCatalogueService, times(1)).saveFoodItem(foodItemDTO);
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody() == foodItemDTO;
    }

    @Test
    void fetchRestauDetailsWithFoodMenu_ShouldReturnOkStatus() {
        // Arrange
        int restaurantId = 123;
        FoodCataloguePageDTO foodCataloguePage = new FoodCataloguePageDTO();
        when(foodCatalogueService.fetchFoodCataloguePageDetailsByRestaurantId(restaurantId)).thenReturn(foodCataloguePage);

        // Act
        ResponseEntity<FoodCataloguePageDTO> response = foodCatalogueController.fetchRestaurantAndFoodItemsById(restaurantId);

        // Assert
        verify(foodCatalogueService, times(1)).fetchFoodCataloguePageDetailsByRestaurantId(restaurantId);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() == foodCataloguePage;
    }
}
