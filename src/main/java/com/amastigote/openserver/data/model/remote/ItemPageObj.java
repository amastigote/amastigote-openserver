package com.amastigote.openserver.data.model.remote;

import com.amastigote.openserver.data.model.local.Item;

import java.util.List;

public class ItemPageObj {

    private boolean isFirst;

    private boolean isLast;

    private int currentPage;

    private List<Item> items;

    public int getCurrentPage() {
        return currentPage;
    }

    public ItemPageObj setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public boolean getIsFirst() {
        return isFirst;
    }

    public ItemPageObj setIsFirst(boolean first) {
        isFirst = first;
        return this;
    }

    public boolean getIsLast() {
        return isLast;
    }

    public ItemPageObj setIsLast(boolean last) {
        isLast = last;
        return this;
    }

    public List<Item> getItems() {
        return items;
    }

    public ItemPageObj setItems(List<Item> items) {
        this.items = items;
        return this;
    }
}
