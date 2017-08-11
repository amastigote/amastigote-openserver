package com.amastigote.openserver.data.service;

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

    @Autowired
    public ItemServiceImpl(ItemRepo itemRepo, TagService tagService) {
        this.itemRepo = itemRepo;
        this.tagService = tagService;
    }

    @Override
    @Transactional
    public Item saveWithTagMetas(Item item, String[] tagMetas) {
        List<Tag> tags = tagService.saveWithMetas(tagMetas);
        itemRepo.save(item.setTags(tags));
        return itemRepo.findItemByUrl(item.getUrl());
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
    public Page<Item> findItemsByTagNamesPageable(String[] names, Pageable pageable) {
        return itemRepo.findByTags(tagService.findTagsByNames(names), pageable);
    }

    @Override
    public Page<Item> findAllPageable(Pageable pageable) {
        return itemRepo.findAll(pageable);
    }
}
