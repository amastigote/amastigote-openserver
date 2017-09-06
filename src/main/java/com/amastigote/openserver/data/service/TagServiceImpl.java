package com.amastigote.openserver.data.service;

import com.amastigote.openserver.data.model.local.Tag;
import com.amastigote.openserver.data.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

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
    public String getTagTableHash() {
        return DigestUtils
                .md5DigestAsHex(String.valueOf(
                        tagRepository.findMaxId()
                ).getBytes());
    }

    @Override
    public List<String> findAllTagNames() {
        return tagRepository.findAll()
                .parallelStream()
                .map(Tag::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<Tag> findTagsByNames(String[] names) {
        return Arrays.asList(names)
                .parallelStream()
                .map(this::findTagByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
