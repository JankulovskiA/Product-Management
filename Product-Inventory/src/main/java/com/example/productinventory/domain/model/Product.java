package com.example.productinventory.domain.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    Integer availability;
    String image;
    @ManyToOne
    Manufacturer manufacturer;
    @ManyToOne
    Category category;

    public Product(String name, String description, String image, Manufacturer manufacturer, Category category) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.manufacturer = manufacturer;
        this.category = category;
        this.availability=0;
    }

    public void add(int quantity)
    {
        this.availability+=quantity;
    }

    public void subtract(int quantity)
    {
        availability-=quantity;
    }
}