package com.example.ratatouilleapp.Model.Api;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("search.php")
    Single<MealResponse> searchMealByName(@Query("s") String mealName);

    @GET("search.php")
    Single<MealResponse> listMealsByFirstLetter(@Query("f") String firstLetter);

    @GET("lookup.php")
    Single<MealResponse> lookupMealById(@Query("i") String mealId);

    @GET("random.php")
    Single<MealResponse> getRandomMeal();

    @GET("categories.php")
    Single<CategoryResponse> listMealCategories();

    @GET("filter.php")
    Single<MealResponse> filterByIngredient(@Query("i") String ingredient);

    @GET("filter.php")
    Single<MealResponse> filterByCategory(@Query("c") String category);

    @GET("filter.php")
    Single<MealResponse> filterByArea(@Query("a") String area);

    @GET("list.php?a=list")
    Single<AreaResponse> getAllAreas();


    @GET("list.php?i=list")
    Single<IngredientResponse> getAllIngredients();
}
