package com.example.ratatouilleapp.Presenter;

import androidx.lifecycle.Observer;

import com.example.ratatouilleapp.Model.DB.FavMeal.FavMeal;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.View.Home.FavoriteView.Ifav;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavPresenter {
    private Irepo repo;
    private Ifav view;

    public FavPresenter (Irepo repo,Ifav view)
    {
        this.repo=repo;
        this.view=view;
    }

    public void getFavList()
    {
        repo.getStoredFavMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favMeals -> {
                            view.ShowMealFavorite(favMeals);
                        }
                );
    }
    public void insert(FavMeal meal)
    {
        repo.insert(meal);
    }
    public void  delet(FavMeal meal)
    {
        repo.delet(meal);
    }

}
