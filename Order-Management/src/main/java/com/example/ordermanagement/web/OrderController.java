package com.example.ordermanagement.web;

import com.example.ordermanagement.domain.model.Item;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.service.OrderItemService;
import com.example.ordermanagement.service.OrderService;
import com.example.sharedkernel.enumerations.OrderType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;
    private final ProductClient productClient;
    private final OrderItemService orderItemService;


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
    public String addOrderItem(@RequestParam(required = false) String error,
                               @RequestParam Long orderId,
                               @RequestParam Long productId,
                               @RequestParam Integer quantity,
                               @RequestParam Integer price,
                               Model model)
    {
        if(error != null && !Objects.equals(error, "null"))
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        Order order=orderService.findById(orderId);
        if(orderService.canAdd(order.getOrderType(),quantity,productId))
        {
            Order tmp=orderService.addOrderItem(orderId,productId,productClient.findById(productId).getName(),quantity,price);
            model.addAttribute("bodyContent","add-order");
            model.addAttribute("orderItems",orderItemService.findAll(order));
            model.addAttribute("order",tmp);

        }
        else {
            model.addAttribute("bodyContent","add-order?Only"+productClient.getAvailability(productId)+"Available");
            model.addAttribute("order",order);
        }
        model.addAttribute("products",productClient.findAll());
        return "master-template";


    }

    @GetMapping("/remove-item/{id}")
    public String removeOrderItem(@PathVariable Long id,@RequestParam Long orderId,Model model)
    {
        Order o=orderService.removeOrderItem(id,orderId);
        model.addAttribute("order",o);
        model.addAttribute("bodyContent","add-order");
        model.addAttribute("products",productClient.findAll());
        model.addAttribute("orderItems",orderItemService.findAll(o));
        return "master-template";
    }

    @PostMapping("/add")
    public String placeOrder(@RequestParam Long orderId,
                             @RequestParam String description){
        orderService.placeOrder(orderId,description);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id)
    {
        orderService.cancelOrder(id);
        return "redirect:/orders";
    }
    //todo:da ne moze da se odzema poveke od koku so ima
    //todo:da gi prikaze samo produktite so gi neame izbereno
    //todo:kako da ne dozvola kreiranje na order ako nema items u nego



}
