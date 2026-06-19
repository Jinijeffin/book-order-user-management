package com.example.bookstore.dto;

import lombok.Data;

@Data
public class BookResponseDto {

    
    private Long id;
    private String title;
    private String author;
    private double price;
    private int stock;


}