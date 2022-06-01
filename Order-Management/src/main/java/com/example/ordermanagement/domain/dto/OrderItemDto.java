package com.example.ordermanagement.domain.dto;

import lombok.Getter;

@Getter
public class OrderItemDto {
    Long orderId;
    Long productId;
    Integer quantity;
    Integer price;
    /*
    {
       "orderId":1,
       "productId":2,
       "quantity":20,
       "price":30
    }
    */
}
