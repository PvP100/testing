package com.example.dictionaryapp.model;

import java.util.List;

public class
CategoryTitle {

    String categoryTitle;
    List<ItemCategory> list;

    public CategoryTitle(String categoryTitle, List<ItemCategory> list) {
        this.categoryTitle = categoryTitle;
        this.list = list;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public List<ItemCategory> getList() {
        return list;
    }

    public void setList(List<ItemCategory> list) {
        this.list = list;
    }
}
