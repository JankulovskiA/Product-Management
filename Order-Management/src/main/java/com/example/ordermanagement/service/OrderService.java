package com.example.ordermanagement.service;

import com.example.ordermanagement.domain.dto.OrderDto;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.Item;
import com.example.sharedkernel.enumerations.OrderType;

import java.util.List;

public interface OrderService {
    public List<Order> findAll();
    public List<Item> listOrderItems(Long orderId);
    public Order addOrderItem(Long orderId,Long productId,String productName,Integer quantity,Integer price);
    public Order removeOrderItem(Long id,Long orderId);
    public Order create(OrderType orderType);
    public Order placeOrder(Long orderId,String description);
    public void cancelOrder(Long orderId);
    public Order update(Long orderId,OrderDto orderDto);
    public Order findById(Long orderId);
}
