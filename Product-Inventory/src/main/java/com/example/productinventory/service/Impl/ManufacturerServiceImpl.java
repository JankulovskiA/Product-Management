package com.example.productinventory.service.Impl;

import com.example.productinventory.domain.exceptions.ManufacturerDoesNotExistException;
import com.example.productinventory.domain.model.Manufacturer;
import com.example.productinventory.domain.repository.ManufacturerRepository;
import com.example.productinventory.service.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    ManufacturerRepository manufacturerRepository;

    @Override
    public Manufacturer findById(Long id) {
        return manufacturerRepository.findById(id).orElseThrow(()->new ManufacturerDoesNotExistException(id));
    }

    @Override
    public Manufacturer create(String name, String address) {
        return manufacturerRepository.save(new Manufacturer(name,address));
    }

    @Override
    public Manufacturer edit(Long id, String name, String address) {
        Manufacturer manufacturer;
        if(manufacturerRepository.findById(id).isPresent())
        {
            manufacturer=manufacturerRepository.findById(id).get();
            manufacturer.setName(name);
            manufacturer.setAddress(address);
            manufacturerRepository.save(manufacturer);
            return manufacturer;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        manufacturerRepository.deleteById(id);
    }

    @Override
    public List<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }
}
