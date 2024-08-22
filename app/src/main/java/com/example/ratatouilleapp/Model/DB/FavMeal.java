package com.example.ratatouilleapp.Model.DB;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meals_tabel")
public class FavMeal {


    @PrimaryKey
    @NonNull
    private String id;


    private String name;

    private String thumbnailUrl;

    public FavMeal(){}


    public FavMeal(String id,String name, String thumbnailUrl)
    {
        this.id=id;
        this.name=name;
        this.thumbnailUrl=thumbnailUrl;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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
}
