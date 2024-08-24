package com.example.ratatouilleapp.Model.Api;

import android.util.Log;

import java.util.List;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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


    public Single<List<Meal>> searchMealByName(String mealName) {
        return apiServices.searchMealByName(mealName).map(MealResponse::getMeals);

//        Call<MealResponse> call = apiServices.searchMealByName(mealName);
//        call.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    networkCallback.onResponseUpdate(response.body().getMeals());
//                } else {
//                    networkCallback.onFailure(new Throwable("No meals found"));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                networkCallback.onFailure(t);
//            }
//        });
    }

    public Single<List<Meal>> getMealsByFirstLetter(String letter) {
        return apiServices.listMealsByFirstLetter(letter).map(MealResponse::getMeals);
//        Call<MealResponse> call = apiServices.listMealsByFirstLetter(letter);
//        call.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    networkCallback.onResponseUpdate(response.body().getMeals());
//                } else {
//                    networkCallback.onFailure(new Throwable("No meals found"));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                networkCallback.onFailure(t);
//            }
//        });
    }

    public Single<List<Meal>> getMealById(String id) {
        return apiServices.lookupMealById(id).map(MealResponse::getMeals);
//        Call<MealResponse> call = apiServices.lookupMealById(id);
//        call.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    networkCallback.onResponseUpdate(response.body().getMeals());
//                } else {
//                    networkCallback.onFailure(new Throwable("No meals found"));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                networkCallback.onFailure(t);
//            }
//        });
    }

    public Single<List<Meal>> getRandomMeal() {
        return apiServices.getRandomMeal().map(MealResponse::getMeals);
//        Call<MealResponse> call = apiServices.getRandomMeal();
//        call.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    networkCallback.onResponseUpdate(response.body().getMeals());
//                } else {
//                    networkCallback.onFailure(new Throwable("No meals found"));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                networkCallback.onFailure(t);
//            }
//        });
    }

    public Single<List<Category>> getMealCategories() {
        return apiServices.listMealCategories().map(CategoryResponse::getCategories);
//        Call<CategoryResponse> call = apiServices.listMealCategories();
//        call.enqueue(new Callback<CategoryResponse>() {
//            @Override
//            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    networkCallback.onResponseUpdate(response.body().getCategories());
//                } else {
//                    networkCallback.onFailure(new Throwable("No categories found"));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CategoryResponse> call, Throwable t) {
//                networkCallback.onFailure(t);
//            }
//        });
    }

    public Single<List<Meal>> filterMealsByIngredient(String ingredient) {
        return apiServices.filterByIngredient(ingredient).map(MealResponse::getMeals);
//        Call<MealResponse> call = apiServices.filterByIngredient(ingredient);
//        call.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    networkCallback.onResponseUpdate(response.body().getMeals());
//                } else {
//                    networkCallback.onFailure(new Throwable("No meals found"));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                networkCallback.onFailure(t);
//            }
//        });
    }

    public Single<List<Meal>> filterMealsByCategory(String category) {
        return apiServices.filterByCategory(category).map(MealResponse::getMeals);
//        Call<MealResponse> call = apiServices.filterByCategory(category);
//        call.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    networkCallback.onResponseUpdate(response.body().getMeals());
//                } else {
//                    networkCallback.onFailure(new Throwable("No meals found"));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                networkCallback.onFailure(t);
//            }
//        });
    }

    public Single<List<Meal>> filterMealsByArea(String area) {
        return apiServices.filterByArea(area).map(MealResponse::getMeals);
//        Call<MealResponse> call = apiServices.filterByArea(area);
//        call.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    networkCallback.onResponseUpdate(response.body().getMeals());
//                } else {
//                    networkCallback.onFailure(new Throwable("No meals found"));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                networkCallback.onFailure(t);
//            }
//        });
    }

    public Single<List<Area>> getAllAreas() {
        return apiServices.getAllAreas().map(AreaResponse::getAreas);
//        Call<AreaResponse> call = apiServices.getAllAreas();
//        call.enqueue(new Callback<AreaResponse>() {
//            @Override
//            public void onResponse(Call<AreaResponse> call, Response<AreaResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    callback.onResponseUpdate(response.body().getAreas());
//                } else {
//                    callback.onFailure(new Exception("Failed to load areas"));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AreaResponse> call, Throwable throwable) {
//                callback.onFailure(throwable);
//            }
//        });
    }

    public Single<List<Ingredient>> getAllIngredients() {
        return apiServices.getAllIngredients().map(IngredientResponse::getIngredients);
//        Call<IngredientResponse> call = apiServices.getAllIngredients();
//        call.enqueue(new Callback<IngredientResponse>() {
//            @Override
//            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    callback.onResponseUpdate(response.body().getIngredients());
//                } else {
//                    callback.onFailure(new Exception("Failed to load ingredients"));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<IngredientResponse> call, Throwable throwable) {
//                callback.onFailure(throwable);
//            }
//        });
    }


}
