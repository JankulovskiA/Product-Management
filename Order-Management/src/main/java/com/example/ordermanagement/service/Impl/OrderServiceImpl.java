package com.example.ordermanagement.service.Impl;

import com.example.ordermanagement.domain.dto.OrderDto;
import com.example.ordermanagement.domain.exceptions.ItemDoesNotExistException;
import com.example.ordermanagement.domain.model.Item;
import com.example.ordermanagement.service.OrderItemService;
import com.example.sharedkernel.enumerations.OrderType;
import com.example.ordermanagement.domain.exceptions.OrderDoesNotExistException;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.repository.ItemRepository;
import com.example.ordermanagement.domain.repository.OrderRepository;
import com.example.ordermanagement.service.OrderService;
import com.example.sharedkernel.events.orders.OrderItemCreated;
import com.example.sharedkernel.events.orders.OrderItemRemoved;
import com.example.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final OrderItemService orderItemService;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(()->new OrderDoesNotExistException(orderId));
    }

    @Override
    public List<Item> listOrderItems(Long orderId) {
        return orderItemService.findAll(this.findById(orderId));
    }

    @Override
    public Order create(OrderType orderType) {
        return orderRepository.save(new Order(orderType));
    }

    @Override
    public Order addOrderItem(Long orderId,Long productId,String productName,Integer quantity,Integer price) {
        Order order=this.findById(orderId);
        domainEventPublisher.publish(new OrderItemCreated(productId,quantity,order.getOrderType()));
        Item item = new Item(productId,productName,quantity,price);
        itemRepository.save(item);
        orderItemService.save(order, item);
        order.setTotal(orderItemService.total(orderId));
        order.addItem();
        orderRepository.save(order);
        return order;
    }

    @Override
    @Transactional
    public Order removeOrderItem(Long id,Long orderId) {
        Order order=this.findById(orderId);
        Item item = itemRepository.findById(id).orElseThrow(()->new ItemDoesNotExistException(id));
        domainEventPublisher.publish(new OrderItemRemoved(item.getProductId(), item.getQuantity(),this.findById(orderId).getOrderType()));
        orderItemService.delete(order,item);
        itemRepository.delete(item);
        order.setTotal(orderItemService.total(orderId));
        order.removeItem();
        orderRepository.save(order);
        return this.findById(orderId);
    }

    @Override
    public Order placeOrder(Long orderId,String description) {
        Order order=this.findById(orderId);
        order.setTotal(orderItemService.total(orderId));
        order.setDescription(description);
        orderRepository.save(order);
        return order;
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order=this.findById(orderId);
        List<Item> items=orderItemService.findAll(order);
        orderItemService.deleteAll(order);
        itemRepository.deleteAll(items);
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order update(Long orderId,OrderDto orderDto) {
        Order tmp=orderRepository.findById(orderId)
                .orElseThrow(()->new OrderDoesNotExistException(orderId));
        tmp.setOrderType(orderDto.getOrderType());
        //todo datata dali da moze da se menuva
        //tmp.setDate(orderDto.getDate());
        //tmp.setOrderItemList(orderDto.getOrderItemList());
        return orderRepository.save(tmp);
    }

}
