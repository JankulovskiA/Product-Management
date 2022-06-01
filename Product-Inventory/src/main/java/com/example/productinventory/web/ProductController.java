package com.example.productinventory.web;

import com.example.productinventory.domain.dto.ProductDto;
import com.example.productinventory.domain.model.Category;
import com.example.productinventory.domain.model.Manufacturer;
import com.example.productinventory.domain.model.Product;
import com.example.productinventory.service.CategoryService;
import com.example.productinventory.service.ManufacturerService;
import com.example.productinventory.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("products")
public class ProductController {


    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    @GetMapping
    public String findAll(Model model)
    {
        List<Product> products=this.productService.findAll();
        model.addAttribute("products",products);
        model.addAttribute("bodyContent","products");
        return "master-template";
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id)
    {
        return productService.findById(id);
    }

    @GetMapping("/add-form")
    public String addForm(Model model){
        List<Category> categories=categoryService.findAll();
        List<Manufacturer> manufacturers=manufacturerService.findAll();
        model.addAttribute("categories",categories);
        model.addAttribute("manufacturers",manufacturers);
        model.addAttribute("bodyContent","add-product");
        return "master-template";
    }

    @GetMapping("/edit-form/{id}")
    public String editForm(@PathVariable Long id,Model model)
    {
        if(this.productService.findById(id)!=null){
            Product product=this.productService.findById(id);
            List<Category> categories=categoryService.findAll();
            List<Manufacturer> manufacturers=manufacturerService.findAll();
            model.addAttribute("categories",categories);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("product",product);
            model.addAttribute("bodyContent","add-product");
            return "master-template";
        }
        model.addAttribute("bodyContent","products?error=ProductNotFound");
        return "master-template";
    }

    @PostMapping("/add")
    public String save(@RequestParam(required = false) Long id,
                       @RequestParam String name,
                       @RequestParam String description,
                       @RequestParam Long manufacturer,
                       @RequestParam Long category)
    {
        if(id!=null){
            if(productService.findById(id)!=null)
            {
                productService.edit(id,name,description,manufacturer,category);
            }
        }
        else
            productService.create(name,description,manufacturer,category);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        productService.delete(id);
        return "redirect:/products";
    }
}
