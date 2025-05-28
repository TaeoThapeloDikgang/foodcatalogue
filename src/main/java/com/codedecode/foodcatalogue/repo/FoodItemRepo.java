package com.codedecode.foodcatalogue.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.codedecode.foodcatalogue.entity.FoodItem;

import java.util.List;


@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Integer> {
    List<FoodItem> findByRestaurantId(Integer restaurantId); // todo im still not sure how jpa does this, gets extended with customized methods
}
