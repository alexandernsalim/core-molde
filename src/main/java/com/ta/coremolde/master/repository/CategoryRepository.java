package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findCategoryById(Integer id) throws IllegalArgumentException;

}
