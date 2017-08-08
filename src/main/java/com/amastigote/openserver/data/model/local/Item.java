package com.amastigote.openserver.data.model.local;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String title;

    @Column(unique = true)
    private String url;

    @LazyCollection(value = LazyCollectionOption.FALSE)
    @ManyToMany(targetEntity = Tag.class)
    @JoinTable(
            name = "ItemTagMapper",
            joinColumns = @JoinColumn(name = "itemId"),
            inverseJoinColumns = @JoinColumn(name = "tagId")
    )
    private List<Tag> tags;

    private boolean bumpToTop;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Item setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Item setUrl(String url) {
        this.url = url;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Item setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public boolean isBumpToTop() {
        return bumpToTop;
    }

    public void setBumpToTop(boolean bumpToTop) {
        this.bumpToTop = bumpToTop;
    }
}
