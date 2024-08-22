package com.example.ratatouilleapp.Presenter;

import androidx.lifecycle.Observer;

import com.example.ratatouilleapp.Model.DB.FavMeal;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.View.Favorite.Ifav;

import java.util.List;

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
        repo.getStoredFavMeals().observeForever(new Observer<List<FavMeal>>() {
            @Override
            public void onChanged(List<FavMeal> favMeals) {
                view.ShowMealFavorite(favMeals);
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

}
