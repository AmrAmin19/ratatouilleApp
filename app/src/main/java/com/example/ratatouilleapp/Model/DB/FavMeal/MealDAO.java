package com.example.ratatouilleapp.Model.DB.FavMeal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDAO {
//    @Query("SELECT * FROM meals_tabel")
    @Query("SELECT * FROM meals_tabel WHERE userEmail = :userEmail")
   Flowable<List<FavMeal>> getFavMeals(String userEmail);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMeal(FavMeal meal);

    @Query("DELETE FROM meals_tabel WHERE id = :id AND userEmail = :userEmail")
    Completable  deleteMealById(String id, String userEmail);



}
