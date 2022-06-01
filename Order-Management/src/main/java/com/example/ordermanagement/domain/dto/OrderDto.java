package com.example.ordermanagement.domain.dto;

import com.example.ordermanagement.domain.model.Item;
import com.example.sharedkernel.enumerations.OrderType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private Long orderId;
    private OrderType orderType;
    private Integer total;
    List<Item> itemList;
    private LocalDateTime date;
}
