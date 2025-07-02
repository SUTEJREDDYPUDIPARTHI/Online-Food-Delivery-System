package com.example.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.entity.Payment;
import com.example.util.DBConnectionUtil;

public class PaymentServiceImpl implements PaymentService{
    @Override
    public boolean processPayment(Payment payment){
        String query = "INSERT INTO payment (paymentId, orderId, paymentDate, paymentStatus, amountPaid) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = DBConnectionUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1, payment.getPaymentId());
                stmt.setInt(2, payment.getOrderId());
                stmt.setTimestamp(3, new java.sql.Timestamp(payment.getPaymentDate().getTime()));
                stmt.setString(4, payment.getPaymentStatus());
                stmt.setDouble(5, payment.getAmountPaid());
                
                return stmt.executeUpdate() > 0;
        }catch(SQLException e){
            System.err.println("SQLException occurred while processing payment: " + e.getMessage());
            return false;
        }
    }
}