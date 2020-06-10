package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();

    Category getCategoryById(Integer id);

    String addCategory(String name);

    String updateCategory(Integer id, String updatedName);

    String deleteCategory(Integer id);

}
