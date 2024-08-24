package com.example.ratatouilleapp.Model.DB.FavMeal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MealDAO {
//    @Query("SELECT * FROM meals_tabel")
    @Query("SELECT * FROM meals_tabel WHERE userEmail = :userEmail")
    LiveData<List<FavMeal>> getFavMeals(String userEmail);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(FavMeal meal);

    @Query("DELETE FROM meals_tabel WHERE id = :id AND userEmail = :userEmail")
    void deleteMealById(String id, String userEmail);

    @Query("SELECT EXISTS(SELECT 1 FROM meals_tabel WHERE UniqueId = :mealId LIMIT 1)")
   LiveData <Boolean> hasFavorite(String mealId);

}
