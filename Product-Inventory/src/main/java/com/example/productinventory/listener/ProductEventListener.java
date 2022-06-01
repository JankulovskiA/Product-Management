package com.example.productinventory.listener;

import com.example.productinventory.service.ProductService;
import com.example.sharedkernel.config.TopicHolder;
import com.example.sharedkernel.events.DomainEvent;
import com.example.sharedkernel.events.orders.OrderItemCreated;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductEventListener {
    private final ProductService productService;

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_CREATED,groupId = "productManagement")
    @Payload(required = false)
    public void OrderItemCreatedListener(String jsonMessage)
    {
        try {
            OrderItemCreated event= DomainEvent.fromJson(jsonMessage,OrderItemCreated.class);
            productService.orderItemCreated(event.getProductId(),event.getQuantity(),event.getOrderType());
        }
        catch (Exception e)
        {
            System.out.println("greska");
        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_REMOVED,groupId = "productManagement")
    public void OrderItemRemovedListener(String jsonMessage)
    {
        try {
            OrderItemCreated event= DomainEvent.fromJson(jsonMessage,OrderItemCreated.class);
            productService.orderItemRemoved(event.getProductId(),event.getQuantity(),event.getOrderType());
        }
        catch (Exception e)
        {

        }
    }


}


