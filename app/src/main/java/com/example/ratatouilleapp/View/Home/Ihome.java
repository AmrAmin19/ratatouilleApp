package com.example.ratatouilleapp.View.Home;

import com.example.ratatouilleapp.Model.Api.Area;
import com.example.ratatouilleapp.Model.Api.Category;
import com.example.ratatouilleapp.Model.Api.Meal;

import java.util.List;

public interface Ihome {

    void  showCategories(List<Category> categories);
    void showError(String message);
    void  showMeals(List<Meal> meals);
    void  showAreas(List<Area> areas);
    void showMealsByArea(List<Meal> meals);
    void showRandomMeal(List<Meal> meals);

    //public void  showMeals(List<Meal> meals);
}
