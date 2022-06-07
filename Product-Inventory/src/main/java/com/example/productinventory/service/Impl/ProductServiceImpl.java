package com.example.productinventory.service.Impl;

import com.example.productinventory.domain.dto.ProductDto;
import com.example.productinventory.domain.exceptions.CategoryDoesNotExistException;
import com.example.productinventory.domain.exceptions.ManufacturerDoesNotExistException;
import com.example.productinventory.domain.exceptions.NotEnoughAvailableProductsException;
import com.example.productinventory.domain.exceptions.ProductDoesNotExistException;
import com.example.productinventory.domain.model.Category;
import com.example.productinventory.domain.model.Manufacturer;
import com.example.productinventory.domain.model.Product;
import com.example.productinventory.domain.repository.CategoryRepository;
import com.example.productinventory.domain.repository.ManufacturerRepository;
import com.example.productinventory.domain.repository.ProductRepository;
import com.example.productinventory.service.ProductService;
import com.example.sharedkernel.enumerations.OrderType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ManufacturerRepository manufacturerRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProductDoesNotExistException(id));
    }

    @Override
    public Product create(String name,String description,Long manufacturerId,Long categoryId) {
        Manufacturer manufacturer=manufacturerRepository.findById(manufacturerId)
                .orElseThrow(()->new ManufacturerDoesNotExistException(manufacturerId));
        Category category =categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryDoesNotExistException(categoryId));
        return productRepository.save(new Product(name,description, "",manufacturer,category));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product edit(Long id, String name,String description,Long manufacturerId,Long categoryId) {
        Product product=this.findById(id);
        product.setName(name);
        product.setDescription(description);
        product.setManufacturer(manufacturerRepository.findById(manufacturerId)
                    .orElseThrow(()->new ManufacturerDoesNotExistException(manufacturerId)));
        product.setCategory(categoryRepository.findById(categoryId)
                    .orElseThrow(()->new CategoryDoesNotExistException(categoryId)));
        return productRepository.save(product);
    }

    @Override
    public void orderItemCreated(Long productId, int quantity, OrderType orderType) {
        if(orderType.equals(OrderType.EXPORT)) {
            if (quantity > this.findById(productId).getAvailability()) {
                throw new NotEnoughAvailableProductsException(productId);
            }
            Product p=this.findById(productId);
            p.subtract(quantity);
            productRepository.save(p);
        }
        else
        {
            Product p=this.findById(productId);
            p.add(quantity);
            productRepository.save(p);
        }
    }

    @Override
    public void orderItemRemoved(Long productId, int quantity,OrderType orderType) {
        Product p=this.findById(productId);
        if(orderType.equals(OrderType.EXPORT)) {
            p.add(quantity);
        }
        else {
            p.subtract(quantity);
        }
        productRepository.save(p);
    }

}
