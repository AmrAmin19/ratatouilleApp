package com.example.ratatouilleapp.Presenter;

import android.widget.Toast;

import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.Model.Repo.RepoCallback;
import com.example.ratatouilleapp.View.Home.ProfileView.Iprofile;
import com.example.ratatouilleapp.View.Home.ProfileView.ProfileFragment;

public class ProfilePresenter {
    Irepo repo;
    Iprofile view;

   public ProfilePresenter(Irepo repo,Iprofile view)
    {
        this.repo=repo;
        this.view=view;
    }
    //Meals

    public void backUpMeals()
    {
        repo.backupDataToFirestore(new RepoCallback<String>() {
            @Override
            public void onSuccess(String result) {
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
    public void restoreMeals()
    {
        repo.restoreDataFromFirestore(new RepoCallback<String>() {
            @Override
            public void onSuccess(String result) {
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    //Plans

    public void backupPlans()
    {
        repo.backupPlanDataToFirestore(new RepoCallback<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }
    public void restorePlans()
    {
        repo.restorePlanDataFromFirestore(new RepoCallback<String>() {
            @Override
            public void onSuccess(String result) {
            }

            @Override
            public void onError(Throwable throwable) {
            }
        });
    }

    public String getuserEmail()
    {
       return repo.getUserEmail();
    }

    public void logout()
    {
        repo.signOut();
    }

}
