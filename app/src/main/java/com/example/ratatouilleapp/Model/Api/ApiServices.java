package com.example.ratatouilleapp.Model.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("search.php")
    Call<MealResponse> searchMealByName(@Query("s") String mealName);

    @GET("search.php")
    Call<MealResponse> listMealsByFirstLetter(@Query("f") char firstLetter);

    @GET("lookup.php")
    Call<MealResponse> lookupMealById(@Query("i") String mealId);

    @GET("random.php")
    Call<MealResponse> getRandomMeal();

    @GET("categories.php")
    Call<CategoryResponse> listMealCategories();

    @GET("filter.php")
    Call<MealResponse> filterByIngredient(@Query("i") String ingredient);

    @GET("filter.php")
    Call<MealResponse> filterByCategory(@Query("c") String category);

    @GET("filter.php")
    Call<MealResponse> filterByArea(@Query("a") String area);
}
