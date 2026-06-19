package com.example.order_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long bookId;
    private Long userId;
    private Integer quantity;
    private Double price;
    private String status;
    private LocalDateTime createdAt;
}
