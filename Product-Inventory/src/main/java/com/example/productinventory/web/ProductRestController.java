package com.example.productinventory.web;

import com.example.productinventory.domain.model.Product;
import com.example.productinventory.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAll()
    {
        return productService.findAll();
    }
}
