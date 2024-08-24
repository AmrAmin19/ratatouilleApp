package com.example.ratatouilleapp.Model.DB.PlanMeal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface PlanDAO {



    @Query("SELECT * FROM meal_plans WHERE userEmail = :userEmail")
    Flowable<List<Plan>> getPlans(String userEmail);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPlan(Plan plan);

    @Delete
    Completable deletPlan(Plan plan);
//    @Query("DELETE FROM meal_plans WHERE planDate = :date And mealId = :id AND userEmail = :userEmail" )
//    void deletPlan(String date,String id,String userEmail);
}
