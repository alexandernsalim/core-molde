package com.ta.coremolde.service;

public interface CategoryService {

    String addCategory(String name);
    String updateCategory(Integer id, String updatedName);
    String deleteCategory(Integer id);

}
