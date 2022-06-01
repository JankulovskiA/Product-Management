package com.example.productinventory.web;

import com.example.productinventory.domain.model.Category;
import com.example.productinventory.domain.model.Manufacturer;
import com.example.productinventory.service.ManufacturerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manufacturers")
@AllArgsConstructor
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @GetMapping()
    public String findAll(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Manufacturer> manufacturers=manufacturerService.findAll();
        model.addAttribute("manufacturers",manufacturers);
        model.addAttribute("bodyContent","manufacturers");
        return "master-template";
    }

    @PostMapping("/add")
    public String add(@RequestParam String  name,@RequestParam String address)
    {
        if(name.isEmpty() || address.isEmpty())
        {
            return "redirect:/manufacturers?error=PleaseFillAllFields";
        }
        manufacturerService.create(name,address);
        return "redirect:/manufacturers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        manufacturerService.delete(id);
        return "redirect:/manufacturers";
    }
}
