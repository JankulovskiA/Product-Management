package com.example.productinventory.service;

import com.example.productinventory.domain.dto.ProductDto;
import com.example.productinventory.domain.model.Product;
import com.example.sharedkernel.enumerations.OrderType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface ProductService {

    public List<Product> findAll();
    public Product findById(Long id);
    public Product create(String name,String description,Long manufacturerI,Long categoryId);
    public void delete(Long id);
    public Product edit(Long id, String name,String description,Long manufacturerId,Long categoryId);
    public void orderItemCreated(Long productId, int quantity, OrderType orderType);
    public void orderItemRemoved(Long productId, int quantity, OrderType orderType);

}
