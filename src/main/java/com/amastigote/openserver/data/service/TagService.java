package com.amastigote.openserver.data.service;

import com.amastigote.openserver.data.model.local.Tag;

import java.util.List;

public interface TagService {
    Tag findTagByName(String name);

    List<Tag> saveWithMetas(String[] metas);

    String getTagTableHash();

    List<String> findAllTagNames();

    List<Tag> findTagsByNames(String[] names);
}
