package com.example.ratatouilleapp.Presenter;

import androidx.lifecycle.Observer;

import com.example.ratatouilleapp.Model.DB.PlanMeal.Plan;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.View.Home.PlanView.Iplan;

import java.util.List;

public class PlanPresenter {
    Irepo repo;
    Iplan view;

    public PlanPresenter(Irepo repo,Iplan view)
    {
        this.repo=repo;
        this.view=view;
    }

    public void getPlans()
    {
        repo.getStoredPlan().observeForever(new Observer<List<Plan>>() {
            @Override
            public void onChanged(List<Plan> plans) {

                view.showPlans(plans);
            }
        });
    }

    public void inserPlan(Plan plan)
    {
        repo.insertPlan(plan);
    }

    public void deletPlan(Plan plan)
    {
        repo.deletPlan(plan);
    }

}
