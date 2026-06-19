package com.example.order_service.service;

import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.dto.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(OrderRequest request);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long orderId);

    OrderResponse updateOrderStatus(Long orderId, String status);

    String cancelOrder(Long orderId);
}