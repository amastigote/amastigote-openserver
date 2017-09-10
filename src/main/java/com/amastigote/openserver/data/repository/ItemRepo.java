package com.amastigote.openserver.data.repository;

import com.amastigote.openserver.data.model.local.Category;
import com.amastigote.openserver.data.model.local.Item;
import com.amastigote.openserver.data.model.local.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {
    Item findItemByUrl(String url);

    @Query("SELECT i FROM Item i JOIN i.tags t WHERE t IN (?1) AND i.category.name = (?2)")
    Page<Item> findItemsByTagsAndCategoryName(List<Tag> tags, String categoryName, Pageable pageable);

    Page<Item> findItemsByCategory(Category category, Pageable pageable);

    List<Item> findItemsByCategory(Category category);

    @Transactional
    void deleteItemByUrl(String url);
}
