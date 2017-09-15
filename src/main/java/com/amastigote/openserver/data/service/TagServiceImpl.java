package com.amastigote.openserver.data.service;

import com.amastigote.openserver.data.model.local.Tag;
import com.amastigote.openserver.data.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepo tagRepository;

    @Autowired
    public TagServiceImpl(TagRepo tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag findTagByName(String name) {
        return tagRepository.findTagByName(name);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = {"tagList", "tagNameList"}, allEntries = true)
    public List<Tag> saveWithMetas(String[] metas) {
        List<Tag> tags = Arrays.asList(metas)
                .parallelStream()
                .map(name -> {
                    Tag thisTag = findTagByName(name);
                    return thisTag == null ? new Tag().setName(name) : thisTag;
                })
                .distinct()
                .collect(Collectors.toList());
        return tagRepository.save(tags);
    }

    @Override
    @Cacheable("tagList")
    public List<String> findAllTagNames() {
        return tagRepository.findAll()
                .parallelStream()
                .map(Tag::getName)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable("tagNameList")
    public List<Tag> findTagsByNames(String[] names) {
        return Arrays.asList(names)
                .parallelStream()
                .map(this::findTagByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
