package com.azore.compustore.fiuady.db;

import android.icu.util.ULocale;

/**
 * Created by Armín on 28/03/2017.
 */

public class CategoryProduct {

    private  int id;
    private String description;


    public CategoryProduct(int id, String description){
        this.id=id;
        this.description=description;
    }

    public int getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
