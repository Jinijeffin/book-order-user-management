package com.example.order_service.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {

    private Long orderId;
    private String bookTitle;
    private String userName;
    private Integer quantity;
    private Double price;
    private String status;
    private LocalDateTime createdAt;


}
