package com.example.ratatouilleapp.Model.Repo;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

import com.example.ratatouilleapp.Model.Api.Area;
import com.example.ratatouilleapp.Model.Api.Category;
import com.example.ratatouilleapp.Model.Api.Ingredient;
import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.Api.NetworkCallback;
import com.example.ratatouilleapp.Model.Api.NetworkManger;
import com.example.ratatouilleapp.Model.DB.FavMeal.AppDatabase;
import com.example.ratatouilleapp.Model.DB.FavMeal.FavMeal;
import com.example.ratatouilleapp.Model.DB.FavMeal.MealDAO;
import com.example.ratatouilleapp.Model.Firebase.IfireBaseAuth;

import java.util.List;

public class Respiratory implements Irepo{
    private Context context;
    private  static Respiratory repo = null;
    private IfireBaseAuth ifireBaseAuth;
    private NetworkManger networkManger;
    private MealDAO mealDAO;
    private LiveData<List<FavMeal>> storedMeal;

    private Respiratory(Context context , IfireBaseAuth ifireBaseAuth)
    {
        this.context=context;
        this.ifireBaseAuth=ifireBaseAuth;
        this.networkManger = NetworkManger.getInstance();
        AppDatabase db= AppDatabase.getInstance(context.getApplicationContext());
        mealDAO = db.getMealDao();
        storedMeal=mealDAO.getFavMeals();


    }

    public static Respiratory getInstance(Context context,IfireBaseAuth ifireBaseAuth)
    {
        if (repo==null)
        {
            repo=new Respiratory(context,ifireBaseAuth);
        }
        return repo;
    }


    @Override
    public void signIn(String email, String password, IfireBaseAuth.AuthCallback callback) {

        ifireBaseAuth.signIn(email,password,callback);

//        SharedPreferences sharedPreferences = ((context).getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE));
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("isSignedIn", true);
//        editor.apply();


    }

    @Override
    public void signUp(String email, String password, IfireBaseAuth.AuthCallback callback) {

        ifireBaseAuth.signUp(email,password,callback);
    }

    @Override
    public void signOut() {

        ifireBaseAuth.signOut();

        SharedPreferences sharedPreferences = (context).getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isSignedIn", false);
        editor.apply();

    }



    // Network Manger
    @Override
    public void searchMealByName(String mealName, final RepoCallback<List<Meal>> callback) {
        networkManger.searchMealByName(mealName, new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void getMealsByFirstLetter(String letter, final RepoCallback<List<Meal>> callback) {
        networkManger.getMealsByFirstLetter(letter, new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void getMealById(String mealId, final RepoCallback<List<Meal>> callback) {
        networkManger.getMealById(mealId, new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void getRandomMeal(final RepoCallback<List<Meal>> callback) {
        networkManger.getRandomMeal(new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void getMealCategories(final RepoCallback<List<Category>> callback) {
        networkManger.getMealCategories(new NetworkCallback<List<Category>>() {
            @Override
            public void onResponseUpdate(List<Category> categories) {
                callback.onSuccess(categories);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void filterMealsByIngredient(String ingredient, final RepoCallback<List<Meal>> callback) {
        networkManger.filterMealsByIngredient(ingredient, new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void filterMealsByCategory(String category, final RepoCallback<List<Meal>> callback) {
        networkManger.filterMealsByCategory(category, new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void filterMealsByArea(String area, final RepoCallback<List<Meal>> callback) {
        networkManger.filterMealsByArea(area, new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void getIngrediants(final RepoCallback<List<Ingredient>> callback)
    {
        networkManger.getAllIngredients(new NetworkCallback<List<Ingredient>>() {
            @Override
            public void onResponseUpdate(List<Ingredient> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);

            }
        });
    }


    @Override
    public void getAreas(final RepoCallback<List<Area>> callback)
    {
        networkManger.getAllAreas(new NetworkCallback<List<Area>>() {
            @Override
            public void onResponseUpdate(List<Area> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);

            }
        });
    }

    // Database
    public LiveData<List<FavMeal>> getStoredFavMeals()
    {
        return  storedMeal;
    }

    public  void  delet(FavMeal meal)
    {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deletMeal(meal);
            }
        }).start();
    }

    public void insert(FavMeal meal)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertMeal(meal);
            }
        }).start();
    }

    //change with Rx or bool  ------


    public LiveData<Boolean> getFavMealById(String mealId) {

            return mealDAO.hasFavorite(mealId);

    }



}
