package com.example.ordermanagement.service;

import com.example.ordermanagement.domain.dto.OrderDto;
import com.example.ordermanagement.domain.dto.OrderItemDeleteDto;
import com.example.ordermanagement.domain.dto.OrderItemDto;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderItem;
import com.example.sharedkernel.enumerations.OrderType;
import org.aspectj.weaver.ast.Or;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    public List<Order> findAll();
    public List<OrderItem> listOrderItems(Long orderId);
    public Order addOrderItem(Long orderId,Long productId,String productName,Integer quantity,Integer price);
    public Order removeOrderItem(OrderItemDeleteDto orderItemDeleteDto);
    public Order create(OrderType orderType);
    public Order placeOrder(OrderDto orderDto);
    public void cancelOrder(Long orderId);
    public Order update(Long orderId,OrderDto orderDto);
    public Order findById(Long orderId);
}
