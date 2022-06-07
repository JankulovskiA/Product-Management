package com.example.ordermanagement.domain.model;
import com.example.sharedkernel.enumerations.OrderType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime date;
    private Integer itemNumber;
    private boolean active;

    public Order(OrderType orderType) {
        this.orderType = orderType;
        this.date = LocalDateTime.now();
        this.description="";
        this.total=0;
        this.itemNumber=0;
        this.active=false;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addItem(){
        this.itemNumber+=1;
    }

    public void setActive() {
        this.active = true;
    }

    public void removeItem()
    {
        this.itemNumber-=1;
    }
}
