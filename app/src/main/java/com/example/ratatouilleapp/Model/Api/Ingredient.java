package com.example.ratatouilleapp.Model.Api;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("strIngredient")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
