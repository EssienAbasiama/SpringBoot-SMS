package com.stockmgs.stockmgsspringboot.Controller;

import com.stockmgs.stockmgsspringboot.Models.Category;
import com.stockmgs.stockmgsspringboot.Repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories/create")
    public String create(){
        return "category/create";
    }

//    @RequestMapping(value = "/categories/add", method = RequestMethod.POST)
//OR

    @PostMapping("/categories/add")
    public  String addCategory(String name, String description){
        Category category = new Category(name, description);
        categoryRepository.save(category);
        return "redirect:/categories/list";
    }

    @GetMapping("/categories/list")
    public String getAllCategories(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        return "category/list";
    }

    @GetMapping("/categories/edit/{id}")
    public String editCategory(@PathVariable("id") int id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "/category/edit";
    }

    @PostMapping("/categories/update")
    public String update(int id, String name, String description){
        var category = categoryRepository.findById(id).get();
        category.setName(name);
        category.setDescription(description);
        categoryRepository.save(category);
        return "redirect:/categories/list";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id){
        var category = categoryRepository.findById(id).get();
        categoryRepository.delete(category);
        return "redirect:/categories/list";
    }
}
