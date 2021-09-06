package com.stockmgs.stockmgsspringboot.Controller;

import com.stockmgs.stockmgsspringboot.Models.Category;
import com.stockmgs.stockmgsspringboot.Models.Stock;
import com.stockmgs.stockmgsspringboot.Repository.StockRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class StockController {
    final StockRepository stockRepository;

    public StockController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    @GetMapping("/stock/create")
    public String create(){
        return "stock/create";
    }

    @PostMapping("/stocks/add")
    public  String addStock(String name, String description){
        Stock stock = new Stock(name, description);
        stockRepository.save(stock);
        return "redirect:stock/list";
    }

    @GetMapping("/stock/list")
    public String listStocks(Model model) {
        model.addAttribute("stocks", stockRepository.findAll());
        return "stock/list";
    }

    @GetMapping("/stock/edit")
    public String editStock(@PathVariable("id") int id, Model model){
        model.addAttribute("stock", stockRepository.findById(id).get());
        return "redirect:/stocks/update";
    }

    @PostMapping("stocks/update")
    public String update(int id, String name, int quantity,double costPrice,double sellingPrice, Category category, String sku) {
        var stock = stockRepository.findById(id).get();
        stock.setName(name);
        stock.setQuantity(quantity);
        stock.setCostPrice(costPrice);
        stock.setSellingPrice(sellingPrice);
        stock.setCategory(category);
        stock.setSku(sku);
        stockRepository.save(stock);
        return "redirect:/stock/list";
    }

    @GetMapping("stocks/delete")
    public String delete(@PathVariable("id")int id) {
        var stock = stockRepository.findById(id).get();
        stockRepository.delete(stock);
        return "redirect:/stocks/list";
    }
}

