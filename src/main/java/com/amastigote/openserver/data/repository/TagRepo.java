package com.amastigote.openserver.data.repository;

import com.amastigote.openserver.data.model.local.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag, Long> {
    Tag findTagByName(String name);

    List<Tag> findAll();

    @Query("SELECT MAX(id) FROM Tag")
    Long findMaxId();
}
