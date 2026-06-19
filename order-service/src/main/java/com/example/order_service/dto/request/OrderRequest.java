package com.example.order_service.dto.request;

import lombok.Data;

@Data
public class OrderRequest {
    private Long bookId;
    private Long userId;
    private Integer quantity;
}
