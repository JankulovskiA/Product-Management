package com.example.productinventory.service;

import com.example.productinventory.domain.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    public Manufacturer findById(Long id);
    public Manufacturer create(String name,String address);
    public Manufacturer edit(Long id,String name,String address);
    public void delete(Long id);
    public List<Manufacturer> findAll();
}
