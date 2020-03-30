package com.ta.coremolde.service;

import com.ta.coremolde.model.entity.Category;

public interface CategoryService {

    Category getCategoryById(Integer id);

    String addCategory(String name);

    String updateCategory(Integer id, String updatedName);

    String deleteCategory(Integer id);

}
