package com.example.ordermanagement.web;

import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.service.OrderService;
import com.example.sharedkernel.enumerations.OrderType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductClient productClient;


    @GetMapping
    public String findAll(Model model)
    {
        model.addAttribute("orders",orderService.findAll());
        model.addAttribute("bodyContent","orders");
        return "master-template";
    }

    @GetMapping("/step1")
    public String chooseType(Model model)
    {
        model.addAttribute("bodyContent","choose-type");
        return "master-template";
    }

    @GetMapping("/add-form/{orderType}")
    public String createOrder(@PathVariable String orderType,Model model)
    {
        Order o=null;
        if(orderType.equals("Import"))
            o=orderService.create(OrderType.IMPORT);
        else
            o=orderService.create(OrderType.EXPORT);
        model.addAttribute("order",o);
        model.addAttribute("bodyContent","add-order");
        model.addAttribute("products",productClient.findAll());
        return "master-template";
    }

    @PostMapping("/add-item")
    public String addOrderItem(@RequestParam Long orderId,
                               @RequestParam Long productId,
                               @RequestParam Integer quantity,
                               @RequestParam Integer price,
                               Model model)
    {
        Order o=orderService.addOrderItem(orderId,productId,productClient.findById(productId).getName(),quantity,price);
        model.addAttribute("order",o);
        model.addAttribute("bodyContent","add-order");
        model.addAttribute("products",productClient.findAll());
        model.addAttribute("orderItems",o.getOrderItemList());
        return "master-template";
    }

    @GetMapping("/delete-item/{id}")
    public String deleteOrderItem(@PathVariable Long id)
    {
        return "";
    }




}
