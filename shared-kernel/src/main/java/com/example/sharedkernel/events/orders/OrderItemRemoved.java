package com.example.sharedkernel.events.orders;

import com.example.sharedkernel.config.TopicHolder;
import com.example.sharedkernel.enumerations.OrderType;
import com.example.sharedkernel.events.DomainEvent;
import lombok.Getter;

import java.time.Instant;

@Getter
public class OrderItemRemoved  extends DomainEvent {
    private Long productId;
    private int quantity;
    private OrderType orderType;

    public OrderItemRemoved(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
    }

    public OrderItemRemoved(Long productId, int quantity, OrderType orderType) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
        this.productId = productId;
        this.quantity = quantity;
        this.orderType=orderType;
    }
}
