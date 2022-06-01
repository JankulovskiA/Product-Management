package com.example.ordermanagement.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long productId;
    String productName;
    Integer quantity;
    Integer price;

    public OrderItem(Long productId,String productName, Integer quantity, Integer price) {
        this.productId = productId;
        this.productName=productName;
        this.quantity = quantity;
        this.price = price;
    }
}
