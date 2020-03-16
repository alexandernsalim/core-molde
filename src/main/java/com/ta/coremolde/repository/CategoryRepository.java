package com.ta.coremolde.repository;

import com.ta.coremolde.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findCategoryById(Integer id) throws IllegalArgumentException;

}
