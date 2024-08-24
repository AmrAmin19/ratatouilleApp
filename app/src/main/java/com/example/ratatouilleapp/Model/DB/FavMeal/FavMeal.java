package com.example.ratatouilleapp.Model.DB.FavMeal;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meals_tabel")
public class FavMeal {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int UniqueId;

    private String id;


    private String name;

    private String thumbnailUrl;

    private String userEmail;

   // public FavMeal(){}


//    public FavMeal(String id,String name, String thumbnailUrl)
//    {
//        this.id=id;
//        this.name=name;
//        this.thumbnailUrl=thumbnailUrl;
//
//    }

//    public FavMeal(String id,String name, String thumbnailUrl,String userEmail)
//    {
//        this.id=id;
//        this.name=name;
//        this.thumbnailUrl=thumbnailUrl;
//        this.userEmail=userEmail;
//    }


    public String getId() {
        return id;
    }

    public void setId( String id) {
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUniqueId() {
        return UniqueId;
    }
//
    public void setUniqueId(int uniqueId) {
        UniqueId = uniqueId;
    }
}
