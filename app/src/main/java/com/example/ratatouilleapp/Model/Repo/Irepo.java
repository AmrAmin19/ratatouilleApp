package com.example.ratatouilleapp.Model.Repo;

import androidx.lifecycle.LiveData;

import com.example.ratatouilleapp.Model.Api.Area;
import com.example.ratatouilleapp.Model.Api.Category;
import com.example.ratatouilleapp.Model.Api.Ingredient;
import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.DB.FavMeal.FavMeal;
import com.example.ratatouilleapp.Model.DB.PlanMeal.Plan;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public interface Irepo {
    void signIn(String email, String password, RepoAuthCallback callback);
    void signUp(String email, String password, RepoAuthCallback callback);
    void signOut();


     Single<List<Meal>> searchMealByName(String mealName);
     Single<List<Meal>> getMealsByFirstLetter(String letter);
     Single<List<Meal>> getMealById(String mealId);
     Single<List<Meal>> getRandomMeal();
     Single<List<Category>> getMealCategories();
     Single<List<Meal>> filterMealsByIngredient(String ingredient);
     Single<List<Meal>> filterMealsByCategory(String category);
     Single<List<Meal>> filterMealsByArea(String area);
     Single<List<Ingredient>> getIngrediants();
     Single<List<Area>> getAreas();


      void  delet(FavMeal meal);
     void insert(FavMeal meal);
      Flowable<List<FavMeal>> getStoredFavMeals();


     Flowable<List<Plan>> getStoredPlan();
     void insertPlan(Plan plan);
   void deletPlan(Plan plan);

     String getUserEmail();


     void backupDataToFirestore(RepoCallback<String> callback);
     void restoreDataFromFirestore(RepoCallback<String> callback);

    void restorePlanDataFromFirestore(RepoCallback<String> callback);

     void backupPlanDataToFirestore(RepoCallback<String> callback);


}
