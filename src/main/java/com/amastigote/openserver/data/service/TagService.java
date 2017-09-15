package com.amastigote.openserver.data.service;

import com.amastigote.openserver.data.model.local.Tag;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface TagService {
    Tag findTagByName(String name);

    List<Tag> saveWithMetas(String[] metas);

    List<String> findAllTagNames();

    List<Tag> findTagsByNames(String[] names);
}
