package com.stockmgs.stockmgsspringboot.Repository;

import com.stockmgs.stockmgsspringboot.Models.Category;
import com.stockmgs.stockmgsspringboot.Models.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Integer> {
    Stock findByName(String name);
}
