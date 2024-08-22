package com.example.ratatouilleapp.Model.Repo;

import androidx.lifecycle.LiveData;

import com.example.ratatouilleapp.Model.Api.Area;
import com.example.ratatouilleapp.Model.Api.Category;
import com.example.ratatouilleapp.Model.Api.Ingredient;
import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.DB.FavMeal.FavMeal;
import com.example.ratatouilleapp.Model.DB.PlanMeal.Plan;
import com.example.ratatouilleapp.Model.Firebase.IfireBaseAuth;

import java.util.List;

public interface Irepo {
    void signIn(String email, String password, IfireBaseAuth.AuthCallback callback);
    void signUp(String email, String password, IfireBaseAuth.AuthCallback callback);
    void signOut();

     void searchMealByName(String mealName, final RepoCallback<List<Meal>> callback);
     void getMealsByFirstLetter(String letter, final RepoCallback<List<Meal>> callback);
     void getMealById(String mealId, final RepoCallback<List<Meal>> callback);
     void getRandomMeal(final RepoCallback<List<Meal>> callback);
     void getMealCategories(final RepoCallback<List<Category>> callback);
     void filterMealsByIngredient(String ingredient, final RepoCallback<List<Meal>> callback);
     void filterMealsByCategory(String category, final RepoCallback<List<Meal>> callback);
     void filterMealsByArea(String area, final RepoCallback<List<Meal>> callback);

     void getIngrediants(final RepoCallback<List<Ingredient>> callback);
     void getAreas(final RepoCallback<List<Area>> callback);

      void  delet(FavMeal meal);
     void insert(FavMeal meal);
     LiveData<List<FavMeal>> getStoredFavMeals();


     LiveData<List<Plan>> getStoredPlan();
     void insertPlan(Plan plan);
   void deletPlan(Plan plan);


}
