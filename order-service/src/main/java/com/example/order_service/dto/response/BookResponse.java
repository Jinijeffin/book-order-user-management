package com.example.order_service.dto.response;

import lombok.Data;

@Data
public class BookResponse {

    private Long id;
    private String title;
    private Double price;
    private Integer stock;
}
