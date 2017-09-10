package com.amastigote.openserver.data.service;

import com.amastigote.openserver.data.model.local.Category;
import com.amastigote.openserver.data.model.local.Item;
import com.amastigote.openserver.data.model.local.Tag;
import com.amastigote.openserver.data.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;
    private final TagService tagService;
    private final CategoryService categoryService;

    @Autowired
    public ItemServiceImpl(ItemRepo itemRepo, TagService tagService, CategoryService categoryService) {
        this.itemRepo = itemRepo;
        this.tagService = tagService;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public Item saveWithTagMetas(Item item, String[] tagMetas, String categoryName) {
        List<Tag> tags = tagService.saveWithMetas(tagMetas);
        Category category = categoryService.findCategoryByName(categoryName);
        if (category == null)
            return null;
        else {
            item.setCategory(category);
            itemRepo.save(item.setTags(tags));
            return itemRepo.findItemByUrl(item.getUrl());
        }
    }

    @Override
    public Item findItemByURL(String url) {
        return itemRepo.findItemByUrl(url);
    }

    @Override
    @Transactional
    public void deleteItemByURL(String url) {
        itemRepo.deleteItemByUrl(url);
    }

    @Override
    public Page<Item> findItemsByTagsAndCategoryName(String[] names, String categoryName, Pageable pageable) {
        Category category = categoryService.findCategoryByName(categoryName);
        if (category == null)
            return null;
        else {
            return itemRepo.findItemsByTagsAndCategoryName(tagService.findTagsByNames(names), categoryName, pageable);
        }
    }

    @Override
    public Page<Item> findItemsByCategoryName(String categoryName, Pageable pageable) {
        Category category = categoryService.findCategoryByName(categoryName);
        if (category == null)
            return null;
        else {
            return itemRepo.findItemsByCategory(category, pageable);
        }
    }
}
