package com.amastigote.openserver.data.repository;

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

    @Query("SELECT DISTINCT i FROM Item i INNER JOIN i.tags t WHERE t IN (?1)")
    Page<Item> findByTags(List<Tag> tags, Pageable pageable);

    @Transactional
    void deleteItemByUrl(String url);
}
