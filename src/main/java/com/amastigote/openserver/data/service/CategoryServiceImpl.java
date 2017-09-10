package com.amastigote.openserver.data.service;

import com.amastigote.openserver.data.model.local.Category;
import com.amastigote.openserver.data.repository.CategoryRepo;
import com.amastigote.openserver.data.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ItemRepo itemRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, ItemRepo itemRepo) {
        this.categoryRepo = categoryRepo;
        this.itemRepo = itemRepo;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepo.findCategoryByName(name);
    }

    @Override
    @Transactional
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    @Transactional
    public void deleteWithContainingItems(Category category) {
        itemRepo.delete(itemRepo.findItemsByCategory(category));
        categoryRepo.delete(category);
    }
}
