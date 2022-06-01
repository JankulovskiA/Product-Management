package com.example.ordermanagement.web;

import com.example.ordermanagement.domain.dto.OrderDto;
import com.example.ordermanagement.domain.dto.OrderItemDeleteDto;
import com.example.ordermanagement.domain.dto.OrderItemDto;
import com.example.ordermanagement.domain.dto.OrderTypeDto;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderItem;
import com.example.ordermanagement.service.Impl.OrderServiceImpl;
import com.example.ordermanagement.service.OrderService;
import com.example.sharedkernel.enumerations.OrderType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@AllArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable Long id)
    {
        return orderService.findById(id);
    }

    @GetMapping("/list/{id}")
    public List<OrderItem> listOrderItems(@PathVariable Long id)
    {
        return orderService.listOrderItems(id);
    }

    @PostMapping("/create")
    public Order create(@RequestBody OrderTypeDto orderType)
    {
        return orderService.create(orderType.getOrderType());
    }

    //@PostMapping("/addItem")
    //public Order addOrderItem(@RequestBody OrderItemDto orderItemDto)
    //{
    //    return orderService.addOrderItem(orderItemDto);
    //}

    @PostMapping("/deleteItem")
    public Order deleteOrderItem(@RequestBody OrderItemDeleteDto orderItemDeleteDto)
    {
        return orderService.removeOrderItem(orderItemDeleteDto);
    }

    @PostMapping("/saveOrder")
    public Order placeOrder(@RequestBody OrderDto orderDto)
    {
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("/delete/{id}")
    public void cancelOrder(@PathVariable Long id)
    {
        orderService.cancelOrder(id);
    }
}
