package com.example.service;

import java.util.List;

import com.example.entity.Restaurant;

public interface RestaurantService {
    boolean createRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
} 