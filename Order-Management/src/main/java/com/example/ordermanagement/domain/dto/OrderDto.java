package com.example.ordermanagement.domain.dto;

import com.example.sharedkernel.enumerations.OrderType;
import com.example.ordermanagement.domain.model.OrderItem;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    private Long orderId;
    private OrderType orderType;
    private Integer total;
    List<OrderItem> orderItemList;
    private LocalDateTime date;
}
