package com.example.ratatouilleapp.Presenter;

import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.Model.Repo.RepoCallback;
import com.example.ratatouilleapp.View.Home.Idetails;


import java.util.List;


public class DetailsPresenter {
    Irepo repo;
    Idetails view;

    public  DetailsPresenter(Irepo repo,Idetails view)
    {
       this.repo=repo;
       this.view=view;
    }

    public void getMealById(String id)
    {
        repo.getMealById(id, new RepoCallback<List<Meal>>() {
            @Override
            public void onSuccess(List<Meal> result) {
                view.showMeal(result);

            }

            @Override
            public void onError(Throwable throwable) {

                view.showError("no meal Id");
            }
        });
    }
}
