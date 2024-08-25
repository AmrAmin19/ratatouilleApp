package com.example.ratatouilleapp.Presenter;

import androidx.lifecycle.Observer;

import com.example.ratatouilleapp.Model.DB.FavMeal.FavMeal;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.View.Home.HomeView.Ihome;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
        repo.getMealCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        categories -> view.showCategories(categories),
                        throwable -> view.showError(throwable.getMessage())
                );
//        repo.getMealCategories(new RepoCallback<List<Category>>() {
//            @Override
//            public void onSuccess(List<Category> result) {
//                view.showCategories(result);
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//                view.showError("no Data");
//
//            }
//        });
    }

    public void getMealByCategory(String category)
    {
        repo.filterMealsByCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> view.showMeals(meals),
                        throwable -> view.showError(throwable.getMessage())
                );
//        repo.filterMealsByCategory(category, new RepoCallback<List<Meal>>() {
//            @Override
//            public void onSuccess(List<Meal> result) {
//                view.showMeals(result);
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                view.showError("no Meals Data");
//            }
//        });
    }

    public void getAreas()
    {
        repo.getAreas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        areas -> view.showAreas(areas),
                        throwable -> view.showError(throwable.getMessage())
                );
//        repo.getAreas(new RepoCallback<List<Area>>() {
//            @Override
//            public void onSuccess(List<Area> result) {
//               view.showAreas(result);
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                view.showError("no Data");
//                Log.d("Amr", "onError: "+"no");
//
//            }
//        });
    }
    public void filterByArea(String area)
    {
        repo.filterMealsByArea(area)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> view.showMealsByArea(meals),
                        throwable -> view.showError(throwable.getMessage())
                );
//        repo.filterMealsByArea(area, new RepoCallback<List<Meal>>() {
//            @Override
//            public void onSuccess(List<Meal> result) {
//                view.showMealsByArea(result);
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                view.showError("no data");
//
//            }
//        });
    }
    public void getRandomMeal()
    {
        repo.getRandomMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> view.showRandomMeal(meals),
                        throwable -> view.showError(throwable.getMessage())
                );
//        repo.getRandomMeal(new RepoCallback<List<Meal>>() {
//            @Override
//            public void onSuccess(List<Meal> result) {
//                view.showRandomMeal(result);
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                view.showError("no Random Meal ");
//
//            }
//        });
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
//        repo.getUserEmail();
        repo.getStoredFavMeals()
                .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    favMeals -> {
                        view.ShowMealFavorite(favMeals);
                    }
            );
    }



}
