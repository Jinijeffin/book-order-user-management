package com.example.order_service.service;

import com.example.order_service.client.BookClient;
import com.example.order_service.client.UserClient;
import com.example.order_service.dto.request.OrderRequest;
import com.example.order_service.dto.response.BookResponse;
import com.example.order_service.dto.response.OrderResponse;
import com.example.order_service.dto.response.UserResponse;
import com.example.order_service.entity.Order;
import com.example.order_service.exception.InsufficientStockException;
import com.example.order_service.exception.OrderNotFoundException;
import com.example.order_service.mapper.OrderMapper;
import com.example.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements  OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private BookClient bookClient;
    @Autowired
    private UserClient userClient;
    @Override
    public OrderResponse placeOrder(OrderRequest request) {

        BookResponse book = bookClient.getBookById(request.getBookId());//fetch book details
        UserResponse user = userClient.getUserById(request.getUserId());//fetch user details
        if(book.getStock()==null||book.getStock()<request.getQuantity()) // check stock
            throw new InsufficientStockException("Insufficient stock");
        Order order = OrderMapper.toEntity(request);
        order.setStatus("PLACED");
        order.setPrice(request.getQuantity()*book.getPrice());
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        bookClient.reduceStock(request.getBookId(), request.getQuantity()); //reduce stock
        return OrderMapper.toResponse(savedOrder, book, user);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> {
                    BookResponse book = bookClient.getBookById(order.getBookId());
                    UserResponse user = userClient.getUserById(order.getUserId());
                    return OrderMapper.toResponse(order,book,user);
                })
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException(orderId));
        BookResponse book = bookClient.getBookById(order.getBookId());
        UserResponse user = userClient.getUserById(order.getUserId());
        return OrderMapper.toResponse(order,book, user);
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException(orderId));
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        BookResponse book = bookClient.getBookById(order.getBookId());
        UserResponse user = userClient.getUserById(order.getUserId());
        return OrderMapper.toResponse(updatedOrder,book,user);
    }

    @Override
    public String cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new OrderNotFoundException(orderId));
        order.setStatus("CANCELLED");
        orderRepository.save(order);
        bookClient.addStock(order.getBookId(),order.getQuantity());
        return "Successfully cancelled the order";
    }
}
