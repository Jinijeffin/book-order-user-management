package com.example.order_service.client;

import com.example.order_service.dto.response.BookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="bookstore-service")
public interface BookClient {
    @GetMapping("books/{id}")
    BookResponse getBookById(@PathVariable Long id);

    @PatchMapping("/books/{id}/reduce-stock")
    void reduceStock(@PathVariable Long id, @RequestParam int quantity);

    @PatchMapping("/books/{id}/add-stock")
    void addStock(@PathVariable Long id, @RequestParam Integer quantity);
}
