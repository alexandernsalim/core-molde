package com.ta.coremolde.service.impl;

import com.ta.coremolde.model.constant.ResponseConstant;
import com.ta.coremolde.model.entity.Category;
import com.ta.coremolde.repository.CategoryRepository;
import com.ta.coremolde.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

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
            categoryRepository.delete(category);

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
