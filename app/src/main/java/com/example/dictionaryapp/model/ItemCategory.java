package com.example.dictionaryapp.model;

public class ItemCategory {
    private String tilte, resource;

    public ItemCategory(String tilte, String resource) {
        this.tilte = tilte;
        this.resource = resource;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
