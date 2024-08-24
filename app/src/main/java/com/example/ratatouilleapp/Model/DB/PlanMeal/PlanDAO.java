package com.example.ratatouilleapp.Model.DB.PlanMeal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlanDAO {



    @Query("SELECT * FROM meal_plans WHERE userEmail = :userEmail")
    LiveData<List<Plan>> getPlans(String userEmail);

    @Insert
    void insertPlan(Plan plan);

    @Delete
    void deletPlan(Plan plan);
}
