package com.example.service;

import com.example.entity.Payment;

public interface PaymentService {
    boolean processPayment(Payment payment);
}