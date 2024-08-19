package com.example.ratatouilleapp.View.Home;

import com.example.ratatouilleapp.Model.Api.Area;
import com.example.ratatouilleapp.Model.Api.Category;
import com.example.ratatouilleapp.Model.Api.Meal;

import java.util.List;

public interface Ihome {

    public  void  showCategories(List<Category> categories);
    void showError(String message);
    public  void  showMeals(List<Meal> meals);
    public  void  showAreas(List<Area> areas);
    public void showMealsByArea(List<Meal> meals);
    //public void  showMeals(List<Meal> meals);
}
