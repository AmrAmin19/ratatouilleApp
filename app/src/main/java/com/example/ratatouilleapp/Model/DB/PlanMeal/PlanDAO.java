package com.example.ratatouilleapp.Model.DB.PlanMeal;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface PlanDAO {

    LiveData<List<Plan>> getPlans();

    void insertPlan(Plan plan);

    void deletPlan(Plan plan);
}
