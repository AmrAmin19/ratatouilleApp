package com.example.ratatouilleapp.Model.Api;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManger {

    public static final String Tag ="AllProductActivity";
    public static final String BaseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private ApiServices apiServices;
    private Retrofit retrofit;

    private  static  NetworkManger networkManger = null;

    private NetworkManger(){


        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiServices = retrofit.create(ApiServices.class);

    };

    public static NetworkManger getInstance()
    {
        if (networkManger==null)
        {
            return networkManger=new NetworkManger();
        }
        return networkManger;
    }


    public void searchMealByName(String mealName, final NetworkCallback<List<Meal>> networkCallback) {
        Call<MealResponse> call = apiServices.searchMealByName(mealName);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onResponseUpdate(response.body().getMeals());
                } else {
                    networkCallback.onFailure(new Throwable("No meals found"));
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailure(t);
            }
        });
    }

    public void getMealsByFirstLetter(char letter, final NetworkCallback<List<Meal>> networkCallback) {
        Call<MealResponse> call = apiServices.listMealsByFirstLetter(letter);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onResponseUpdate(response.body().getMeals());
                } else {
                    networkCallback.onFailure(new Throwable("No meals found"));
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailure(t);
            }
        });
    }

    public void getMealById(String id, final NetworkCallback<List<Meal>> networkCallback) {
        Call<MealResponse> call = apiServices.lookupMealById(id);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onResponseUpdate(response.body().getMeals());
                } else {
                    networkCallback.onFailure(new Throwable("No meals found"));
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailure(t);
            }
        });
    }

    public void getRandomMeal(final NetworkCallback<List<Meal>> networkCallback) {
        Call<MealResponse> call = apiServices.getRandomMeal();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onResponseUpdate(response.body().getMeals());
                } else {
                    networkCallback.onFailure(new Throwable("No meals found"));
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailure(t);
            }
        });
    }

    public void getMealCategories(final NetworkCallback<List<Category>> networkCallback) {
        Call<CategoryResponse> call = apiServices.listMealCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onResponseUpdate(response.body().getCategories());
                } else {
                    networkCallback.onFailure(new Throwable("No categories found"));
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                networkCallback.onFailure(t);
            }
        });
    }

    public void filterMealsByIngredient(String ingredient, final NetworkCallback<List<Meal>> networkCallback) {
        Call<MealResponse> call = apiServices.filterByIngredient(ingredient);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onResponseUpdate(response.body().getMeals());
                } else {
                    networkCallback.onFailure(new Throwable("No meals found"));
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailure(t);
            }
        });
    }

    public void filterMealsByCategory(String category, final NetworkCallback<List<Meal>> networkCallback) {
        Call<MealResponse> call = apiServices.filterByCategory(category);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onResponseUpdate(response.body().getMeals());
                } else {
                    networkCallback.onFailure(new Throwable("No meals found"));
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailure(t);
            }
        });
    }

    public void filterMealsByArea(String area, final NetworkCallback<List<Meal>> networkCallback) {
        Call<MealResponse> call = apiServices.filterByArea(area);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onResponseUpdate(response.body().getMeals());
                } else {
                    networkCallback.onFailure(new Throwable("No meals found"));
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailure(t);
            }
        });
    }

    public void getAllAreas(NetworkCallback<List<Area>> callback) {
        Call<AreaResponse> call = apiServices.getAllAreas();
        call.enqueue(new Callback<AreaResponse>() {
            @Override
            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponseUpdate(response.body().getAreas());
                } else {
                    callback.onFailure(new Exception("Failed to load areas"));
                }
            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }

    public void getAllIngredients(NetworkCallback<List<Ingredient>> callback) {
        Call<IngredientResponse> call = apiServices.getAllIngredients();
        call.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onResponseUpdate(response.body().getIngredients());
                } else {
                    callback.onFailure(new Exception("Failed to load ingredients"));
                }
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable throwable) {
                callback.onFailure(throwable);
            }
        });
    }


}
