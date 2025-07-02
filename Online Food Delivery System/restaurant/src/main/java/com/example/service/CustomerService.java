package com.example.service;

import com.example.entity.Customer;
import com.example.exception.EmailAlreadyRegisteredException;

public interface CustomerService {
    boolean createCustomer(Customer customer) throws EmailAlreadyRegisteredException;
}