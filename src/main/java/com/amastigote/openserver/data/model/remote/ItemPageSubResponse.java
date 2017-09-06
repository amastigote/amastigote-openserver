package com.amastigote.openserver.data.model.remote;

import com.amastigote.openserver.data.model.local.Item;

import java.util.List;

public class ItemPageSubResponse {

    private boolean isFirst;

    private boolean isLast;

    private int currentPage;

    private List<Item> items;

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public ItemPageSubResponse setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public ItemPageSubResponse setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public boolean getIsFirst() {
        return isFirst;
    }

    public ItemPageSubResponse setIsFirst(boolean first) {
        isFirst = first;
        return this;
    }

    public boolean getIsLast() {
        return isLast;
    }

    public ItemPageSubResponse setIsLast(boolean last) {
        isLast = last;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public ItemPageSubResponse setItems(List<Item> items) {
        this.items = items;
        return this;
    }
}
