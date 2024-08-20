package com.example.ratatouilleapp.Model.Api;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("idCategory")
    private String id;

    @SerializedName("strCategory")
    private String name;

    @SerializedName("strCategoryThumb")
    private String thumbnailUrl;

    @SerializedName("strCategoryDescription")
    private String description;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}