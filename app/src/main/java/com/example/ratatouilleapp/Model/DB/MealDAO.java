package com.example.ratatouilleapp.Model.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ratatouilleapp.Model.Api.Meal;

import java.util.List;

@Dao
public interface MealDAO {
    @Query("SELECT * FROM meals_tabel")
    LiveData<List<FavMeal>> getFavMeals();

    @Insert
    void insertMeal(FavMeal meal);

    @Delete
    void deletMeal(FavMeal meal);

    @Query("SELECT EXISTS(SELECT 1 FROM meals_tabel WHERE id = :mealId LIMIT 1)")
   LiveData <Boolean> hasFavorite(String mealId);

}
