package com.example.productinventory.service;

import com.example.productinventory.domain.model.Category;
import net.bytebuddy.agent.builder.AgentBuilder;

import java.util.List;

public interface CategoryService {
    public Category findById(Long id);
    public Category create(String name);
    public Category edit(Long id,String name);
    public void delete(Long id);
    public List<Category> findAll();
}
