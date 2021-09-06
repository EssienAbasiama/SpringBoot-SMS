package com.stockmgs.stockmgsspringboot.Repository;

import com.stockmgs.stockmgsspringboot.Models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findByName(String name);
}