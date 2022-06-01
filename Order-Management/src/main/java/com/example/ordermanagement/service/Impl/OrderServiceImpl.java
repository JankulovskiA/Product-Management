package com.example.ordermanagement.service.Impl;

import com.example.ordermanagement.domain.dto.OrderDto;
import com.example.ordermanagement.domain.dto.OrderItemDeleteDto;
import com.example.ordermanagement.domain.dto.OrderItemDto;
import com.example.sharedkernel.enumerations.OrderType;
import com.example.ordermanagement.domain.exceptions.OrderDoesNotExistException;
import com.example.ordermanagement.domain.exceptions.OrderItemDoesNotExistException;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderItem;
import com.example.ordermanagement.domain.repository.OrderItemRepository;
import com.example.ordermanagement.domain.repository.OrderRepository;
import com.example.ordermanagement.service.OrderService;
import com.example.sharedkernel.events.orders.OrderItemCreated;
import com.example.sharedkernel.events.orders.OrderItemRemoved;
import com.example.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl  implements OrderService {

    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;
    DomainEventPublisher domainEventPublisher;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(()->new OrderDoesNotExistException(orderId));
    }

    @Override
    public List<OrderItem> listOrderItems(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(()->new OrderDoesNotExistException(orderId))
                .getOrderItemList();
    }

    @Override
    public Order create(OrderType orderType) {
        return orderRepository.save(new Order(orderType));
    }

    @Override
    public Order addOrderItem(Long orderId,Long productId,String productName,Integer quantity,Integer price) {
        Order order=this.findById(orderId);
        domainEventPublisher.publish(new OrderItemCreated(productId,quantity,order.getOrderType()));
        OrderItem orderItem = new OrderItem(productId,productName,quantity,price);
        order.getOrderItemList().add(orderItem);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order removeOrderItem(OrderItemDeleteDto orderItemDeleteDto) {
        Long orderId=orderItemDeleteDto.getOrderId();
        Long orderItemId=orderItemDeleteDto.getOrderItemId();
        OrderItem orderItem=orderItemRepository
                .findById(orderItemId)
                .orElseThrow(()->new OrderItemDoesNotExistException(orderItemId));
        this.findById(orderId).getOrderItemList().remove(orderItem);
        domainEventPublisher.publish(new OrderItemRemoved(orderItem.getProductId(),orderItem.getQuantity(),this.findById(orderId).getOrderType()));
        orderRepository.saveAndFlush(this.findById(orderId));
        return this.findById(orderId);
    }

    @Override
    public Order placeOrder(OrderDto orderDto) {
        Order order=this.findById(orderDto.getOrderId());
        order.setDate(LocalDateTime.now());
        order.setTotal(order.total());
        orderItemRepository.saveAll(order.getOrderItemList());
        orderRepository.saveAndFlush(order);
        return order;
    }

    @Override
    public void cancelOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order update(Long orderId,OrderDto orderDto) {
        Order tmp=orderRepository.findById(orderId)
                .orElseThrow(()->new OrderDoesNotExistException(orderId));
        tmp.setOrderType(orderDto.getOrderType());
        //todo datata dali da moze da se menuva
        //tmp.setDate(orderDto.getDate());
        tmp.setOrderItemList(orderDto.getOrderItemList());
        return orderRepository.save(tmp);
    }

}
