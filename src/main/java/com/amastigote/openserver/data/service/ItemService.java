package com.amastigote.openserver.data.service;

import com.amastigote.openserver.data.model.local.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    Item saveWithTagMetas(Item Item, String[] tagMetas, String categoryName);

    Item findItemByURL(String url);

    void deleteItemByURL(String url);

    Page<Item> findAllByTagsAndCategoryName(String[] names, String categoryName, Pageable pageable);

    Page<Item> findAllByCategoryName(String categoryName, Pageable pageable);
}
