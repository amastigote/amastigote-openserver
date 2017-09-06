package com.amastigote.openserver.data.repository;

import com.amastigote.openserver.data.model.local.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    List<Category> findAll();

    Category findCategoryByName(String name);
}
