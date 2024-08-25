package com.example.ratatouilleapp.Presenter;

import com.example.ratatouilleapp.Model.DB.PlanMeal.Plan;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.View.Home.DetailsView.Idetails;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class DetailsPresenter {
    Irepo repo;
    Idetails view;
    private CompositeDisposable disposable;
    public  DetailsPresenter(Irepo repo,Idetails view)
    {
       this.repo=repo;
       this.view=view;
       this.disposable=new CompositeDisposable();
    }

    public void getMealById(String id)
    {
        disposable.add(
        repo.getMealById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        meals -> view.showMeal(meals),
                        throwable -> view.showError(throwable.getMessage())
                )
        );
//        repo.getMealById(id, new RepoCallback<List<Meal>>() {
//            @Override
//            public void onSuccess(List<Meal> result) {
//                view.showMeal(result);
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//                view.showError("no meal Id");
//            }
//        });
    }
    public void insert(Plan plan)
    {
        repo.insertPlan(plan);
    }
}
