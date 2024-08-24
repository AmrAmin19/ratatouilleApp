package com.example.ratatouilleapp.Model.DB.PlanMeal;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "meal_plans")
public class Plan {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private  int id;

    
    private  String mealId;

    private  String mealName;

    private  String mealImage;

    private  String planDate;

    private String userEmail;


    public Plan(){}


    public Plan(String mealId, String mealName, String mealImage, String planDate,String userEmail) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealImage = mealImage;
        this.planDate = planDate;
        this.userEmail=userEmail;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
