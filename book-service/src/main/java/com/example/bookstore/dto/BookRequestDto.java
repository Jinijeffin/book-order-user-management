package com.example.bookstore.dto;

import lombok.Data;

@Data
public class BookRequestDto {

    private String title;
    private String author;
    private double price;
    private int stock;

}