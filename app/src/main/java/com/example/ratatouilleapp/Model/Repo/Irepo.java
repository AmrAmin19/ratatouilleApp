package com.example.ratatouilleapp.Model.Repo;

import com.example.ratatouilleapp.Model.Api.Category;
import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.Firebase.IfireBaseAuth;

import java.util.List;

public interface Irepo {
    void signIn(String email, String password, IfireBaseAuth.AuthCallback callback);
    void signUp(String email, String password, IfireBaseAuth.AuthCallback callback);
    void signOut();

    public void searchMealByName(String mealName, final RepoCallback<List<Meal>> callback);
    public void getMealsByFirstLetter(char letter, final RepoCallback<List<Meal>> callback);
    public void getMealById(String mealId, final RepoCallback<List<Meal>> callback);
    public void getRandomMeal(final RepoCallback<List<Meal>> callback);
    public void getMealCategories(final RepoCallback<List<Category>> callback);
    public void filterMealsByIngredient(String ingredient, final RepoCallback<List<Meal>> callback);
    public void filterMealsByCategory(String category, final RepoCallback<List<Meal>> callback);
    public void filterMealsByArea(String area, final RepoCallback<List<Meal>> callback);
}
