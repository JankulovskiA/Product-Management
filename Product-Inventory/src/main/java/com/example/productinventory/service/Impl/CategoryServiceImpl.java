package com.example.productinventory.service.Impl;

import com.example.productinventory.domain.exceptions.CategoryDoesNotExistException;
import com.example.productinventory.domain.model.Category;
import com.example.productinventory.domain.repository.CategoryRepository;
import com.example.productinventory.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()->new CategoryDoesNotExistException(id));
    }

    @Override
    public Category create(String name) {
        return categoryRepository.save(new Category(name));
    }

    @Override
    public Category edit(Long id, String name) {
        Category category;
        if(categoryRepository.findById(id).isPresent()) {
            category=categoryRepository.findById(id).get();
            category.setName(name);
            categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


}
