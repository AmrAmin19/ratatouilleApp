package com.example.ratatouilleapp.View.Home;

import com.example.ratatouilleapp.Model.Api.Meal;

import java.util.List;

public interface Idetails {
    void  showMeal(List<Meal> meals);
    void showError(String message);
}
