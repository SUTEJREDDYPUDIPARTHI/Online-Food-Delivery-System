
package com.example.service;

import java.util.List;

import com.example.entity.MenuItem;
import com.example.exception.RestaurantNotFoundException;

public interface MenuService {
    boolean createMenuItem(MenuItem menuItem) throws RestaurantNotFoundException;
    List<MenuItem> getMenuItemsByRestaurant(int restaurantId);
} 
