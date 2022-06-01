package com.example.ordermanagement.service;

import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.Item;
import com.example.ordermanagement.domain.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    Integer total(Long orderId);
    Integer delete(Order order, Item item);
    List<Item> findAll(Order order);
    OrderItem save(Order order,Item item);
    void deleteAll(Order order);
}
