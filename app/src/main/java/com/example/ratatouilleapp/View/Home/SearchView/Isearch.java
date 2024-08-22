package com.example.ratatouilleapp.View.Home.SearchView;

import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.DB.FavMeal;

import java.util.List;

public interface Isearch {
    void showError(String message);
    void  showMeals(List<Meal> meals);

    void ShowMealFavorite(List<FavMeal> favMeals);
}
