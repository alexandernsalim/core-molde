package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.constant.ResponseConstant;
import com.ta.coremolde.master.model.entity.Category;
import com.ta.coremolde.master.repository.CategoryRepository;
import com.ta.coremolde.master.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Integer id) {
        try {
            return categoryRepository.findCategoryById(id);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());

            throw new IllegalArgumentException();
        }
    }

    @Override
    public String addCategory(String name) {
        try {
            categoryRepository.save(Category.builder()
                    .name(name)
                    .build());

            return ResponseConstant.ADD_CATEGORY_SUCCESS;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());

            return ResponseConstant.ADD_CATEGORY_FAILED;
        }
    }

    @Override
    public String updateCategory(Integer id, String updatedName) {
        try {
            Category category = categoryRepository.findCategoryById(id);
            category.setName(updatedName);
            categoryRepository.save(category);

            return ResponseConstant.UPDATE_CATEGORY_SUCCESS;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());

            return ResponseConstant.UPDATE_CATEGORY_FAILED;
        }
    }

    @Override
    public String deleteCategory(Integer id) {
        try {
            Category category = categoryRepository.findCategoryById(id);
            categoryRepository.delete(category);

            return ResponseConstant.DELETE_CATEGORY_SUCCESS;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());

            return ResponseConstant.DELETE_CATEGORY_FAILED;
        }
    }

}
