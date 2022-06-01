package com.example.productinventory.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    String name;
    String description;
    String image;
    Long manufacturerId;
    Long categoryId;
    /*
    {
    "name":"test",
     "description":"test",
     "image":"test",
     "manufacturerId":"1",
     "categoryId":"2"
     }
*/
}
