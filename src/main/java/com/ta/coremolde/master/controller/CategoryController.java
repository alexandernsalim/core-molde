package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.model.constant.PathConstant;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PathConstant.CATEGORY_MAPPING)
public class CategoryController extends GlobalController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(PathConstant.ADD_CATEGORY)
    public Response<String> addCategory(@RequestParam String name) {
        return toResponse(categoryService.addCategory(name));
    }

    @PutMapping(PathConstant.UPDATE_CATEGORY)
    public Response<String> updateCategory(@PathVariable Integer id, @RequestParam String name) {
        return toResponse(categoryService.updateCategory(id, name));
    }

    @DeleteMapping(PathConstant.DELETE_CATEGORY)
    public Response<String> deleteCategory(@PathVariable Integer id) {
        return toResponse(categoryService.deleteCategory(id));
    }

}
