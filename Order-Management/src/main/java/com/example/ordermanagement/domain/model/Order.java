package com.example.ordermanagement.domain.model;
import com.example.sharedkernel.enumerations.OrderType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private OrderType orderType;
    private Integer total;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    List<OrderItem> orderItemList;
    private LocalDateTime date;
    public Integer total()
    {
        return orderItemList.stream().mapToInt(e->e.price*e.quantity).sum();
    }



    public Order(OrderType orderType, List<OrderItem> orderItemList) {
        this.orderType = orderType;
        this.orderItemList = orderItemList;
        total=total();
        this.date = LocalDateTime.now();
    }

    public Order(OrderType orderType) {
        this.orderType = orderType;
        this.date = LocalDateTime.now();
        this.description="";
        this.total=0;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
