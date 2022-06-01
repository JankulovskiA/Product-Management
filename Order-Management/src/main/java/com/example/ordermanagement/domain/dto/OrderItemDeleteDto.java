package com.example.ordermanagement.domain.dto;

import lombok.Getter;

@Getter
public class OrderItemDeleteDto {
    Long orderId;
    Long orderItemId;
}
