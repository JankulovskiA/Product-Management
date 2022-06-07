package com.example.ordermanagement.service.Impl;

import com.example.ordermanagement.domain.exceptions.OrderDoesNotExistException;
import com.example.ordermanagement.domain.model.Item;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderItem;
import com.example.ordermanagement.domain.repository.OrderRepository;
import com.example.ordermanagement.domain.repository.OrderItemRepository;
import com.example.ordermanagement.service.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    @Override
    public Integer total(Long orderId) {
        Order o=orderRepository.findById(orderId)
                .orElseThrow(()->new OrderDoesNotExistException(orderId));
        return orderItemRepository.findAllByOrder(o).stream()
                .mapToInt(e->e.getItem().getPrice()*e.getItem().getQuantity())
                .sum();
    }

    @Override
    public Integer delete(Order order, Item item) {
        return orderItemRepository.deleteByOrderAndItem(order,item);
    }

    @Override
    public List<Item> findAll(Order order) {
        return orderItemRepository.findAllByOrder(order)
                .stream()
                .map(OrderItem::getItem)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItem save(Order order, Item item) {
        return orderItemRepository.save(new OrderItem(order,item));
    }

    @Override
    public void deleteAll(Order order) {
        orderItemRepository.deleteAllByOrder(order);
    }

    @Override
    public void deleteOrder(Order order) {
        this.findAll(order).forEach(e->this.delete(order,e));
    }

}
