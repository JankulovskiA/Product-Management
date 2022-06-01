package com.example.ordermanagement.domain.repository;

import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.Item;
import com.example.ordermanagement.domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findAllByOrder(Order order);
    public Integer deleteByOrderAndItem(Order order, Item item);
    public Integer deleteAllByOrder(Order order);
}
