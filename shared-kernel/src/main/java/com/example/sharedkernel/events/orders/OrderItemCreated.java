package com.example.sharedkernel.events.orders;

import com.example.sharedkernel.config.TopicHolder;
import com.example.sharedkernel.enumerations.OrderType;
import com.example.sharedkernel.events.DomainEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class OrderItemCreated extends DomainEvent {

    @JsonProperty("productId")
    private Long productId;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("orderType")
    private OrderType orderType;

    public OrderItemCreated(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
    }

    public OrderItemCreated(Long productId, int quantity,OrderType orderType) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.productId = productId;
        this.quantity = quantity;
        this.orderType=orderType;
    }
}
