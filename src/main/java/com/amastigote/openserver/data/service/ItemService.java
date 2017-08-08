package com.amastigote.openserver.data.service;

import com.amastigote.openserver.data.model.local.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    void saveWithTagMetas(Item Item, String[] tagMetas);

    Item findItemByURL(String url);

    void deleteItemByURL(String url);

    Page<Item> findItemsByTagNamesPageable(String[] names, Pageable pageable);

    Page<Item> findAllPageable(Pageable pageable);
}
