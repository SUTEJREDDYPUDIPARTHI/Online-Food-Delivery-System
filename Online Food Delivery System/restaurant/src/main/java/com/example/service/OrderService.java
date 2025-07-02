package com.example.service;

import java.util.List;

import com.example.entity.Order;
import com.example.entity.OrderItem;

public interface OrderService {
    boolean createOrder(Order order, List<OrderItem> orderedItems);
    List<Order> getOrdersByCustomer(int customerId);
    Order getOrderById(int orderId);
    boolean updateOrderStatus(int orderId, String status);
}