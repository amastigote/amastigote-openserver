package com.amastigote.openserver.data.service;

import com.amastigote.openserver.data.model.local.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findCategoryByName(String name);

    void save(Category category);

    void deleteWithContainingItems(Category category);
}
