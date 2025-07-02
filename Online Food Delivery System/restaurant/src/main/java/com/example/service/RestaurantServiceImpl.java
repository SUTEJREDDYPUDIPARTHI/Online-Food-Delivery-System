package com.example.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.Restaurant;
import com.example.util.DBConnectionUtil;

public class RestaurantServiceImpl implements RestaurantService{
    @Override
    public boolean createRestaurant(Restaurant restaurant){
        String insertQuery = "INSERT INTO restaurant (name, address, cuisineType, contactNumber) VALUES (?, ?, ?, ?)";
        try(Connection conn = DBConnectionUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                //stmt.setInt(1, restaurant.getRestaurantId());
                stmt.setString(1, restaurant.getName());
                stmt.setString(2, restaurant.getAddress());
                stmt.setString(3, restaurant.getCuisineType());
                stmt.setString(4, restaurant.getContactNumber());

                return stmt.executeUpdate() > 0;
        }catch(SQLException e){
            System.err.println("Error creating restaurant: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Restaurant> getAllRestaurants(){
        List<Restaurant> restaurants = new ArrayList<>();
        String sql = "SELECT * FROM restaurant";
        try(Connection conn = DBConnectionUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
                while(rs.next()){
                    Restaurant restaurant = new Restaurant(
                        rs.getInt("restaurantId"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("cuisineType"),
                        rs.getString("contactNumber")
                    );
                    restaurants.add(restaurant);
                }
        }catch(SQLException e){
            // Log the exception or handle it appropriately
            System.err.println("Error retrieving restaurants: " + e.getMessage());
        }
        return restaurants;
    }
}