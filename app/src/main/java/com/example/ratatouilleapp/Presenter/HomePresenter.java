package com.example.ratatouilleapp.Presenter;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.ratatouilleapp.Model.Api.Area;
import com.example.ratatouilleapp.Model.Api.Category;
import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.DB.FavMeal;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.Model.Repo.RepoCallback;
import com.example.ratatouilleapp.View.Home.HomeView.Ihome;

import java.util.List;

public class HomePresenter {

    Irepo repo;
    Ihome view;

    public HomePresenter(Ihome view,Irepo repo)
    {
        this.view=view;
        this.repo=repo;
    }

    public  void  getMealCategories()
    {
        repo.getMealCategories(new RepoCallback<List<Category>>() {
            @Override
            public void onSuccess(List<Category> result) {
                view.showCategories(result);
            }

            @Override
            public void onError(Throwable throwable) {

                view.showError("no Data");
                
            }
        });
    }

    public void getMealByCategory(String category)
    {
        repo.filterMealsByCategory(category, new RepoCallback<List<Meal>>() {
            @Override
            public void onSuccess(List<Meal> result) {
                view.showMeals(result);
            }

            @Override
            public void onError(Throwable throwable) {
                view.showError("no Meals Data");
            }
        });
    }

    public void getAreas()
    {
        repo.getAreas(new RepoCallback<List<Area>>() {
            @Override
            public void onSuccess(List<Area> result) {
               view.showAreas(result);
            }

            @Override
            public void onError(Throwable throwable) {
                view.showError("no Data");
                Log.d("Amr", "onError: "+"no");

            }
        });
    }
    public void filterByArea(String area)
    {
        repo.filterMealsByArea(area, new RepoCallback<List<Meal>>() {
            @Override
            public void onSuccess(List<Meal> result) {
                view.showMealsByArea(result);
            }

            @Override
            public void onError(Throwable throwable) {
                view.showError("no data");

            }
        });
    }
    public void getRandomMeal()
    {
        repo.getRandomMeal(new RepoCallback<List<Meal>>() {
            @Override
            public void onSuccess(List<Meal> result) {
                view.showRandomMeal(result);

            }

            @Override
            public void onError(Throwable throwable) {
                view.showError("no Random Meal ");

            }
        });
    }

    public void insert(FavMeal meal)
    {
        repo.insert(meal);
    }
    public void  delet(FavMeal meal)
    {
        repo.delet(meal);
    }

    public void getFavList()
    {
        repo.getStoredFavMeals().observeForever(new Observer<List<FavMeal>>() {
            @Override
            public void onChanged(List<FavMeal> favMeals) {
                view.ShowMealFavorite(favMeals);
            }
        });
    }



}
