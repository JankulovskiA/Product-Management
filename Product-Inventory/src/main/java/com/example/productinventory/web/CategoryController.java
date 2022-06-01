package com.example.productinventory.web;

import com.example.productinventory.domain.model.Category;
import com.example.productinventory.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public String findAll(@RequestParam(required = false) String error,Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Category> categoryList=categoryService.findAll();
        model.addAttribute("categories",categoryList);
        model.addAttribute("bodyContent","categories");
        return "master-template";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name)
    {
        if(name.isEmpty())
            return "redirect:/categories?error=PleaseEnterCategoryName";
        categoryService.create(name);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        categoryService.delete(id);
        return "redirect:/categories";
    }

}
