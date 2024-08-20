package com.example.ratatouilleapp.View.Home.SearchView;

import com.example.ratatouilleapp.Model.Api.Meal;

import java.util.List;

public interface Isearch {
    void showError(String message);
    void  showMeals(List<Meal> meals);
}
