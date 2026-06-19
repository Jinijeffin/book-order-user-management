package com.example.order_service.mapper;

import com.example.order_service.client.BookClient;
import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.dto.response.BookResponse;
import com.example.order_service.dto.response.OrderResponse;
import com.example.order_service.dto.response.UserResponse;
import com.example.order_service.entity.Order;

import java.time.LocalDateTime;

public class OrderMapper {

    public static Order toEntity(OrderRequest request){
        Order order = new Order();
        order.setBookId(request.getBookId());
        order.setUserId(request.getUserId());
        order.setQuantity(request.getQuantity());
        return order;
    }
    public static OrderResponse toResponse(Order order, BookResponse book, UserResponse user){
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId());
        response.setBookTitle(book.getTitle());
        response.setUserName(user.getName());
        response.setQuantity(order.getQuantity());
        response.setStatus(order.getStatus());
        response.setPrice(order.getPrice());
        response.setCreatedAt(order.getCreatedAt());
        return response;
    }
}
