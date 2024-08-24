package com.example.ratatouilleapp.Presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ratatouilleapp.Model.Firebase.IfireBaseAuth;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.Model.Repo.RepoAuthCallback;
import com.example.ratatouilleapp.View.Authentication.Ilogin;
import com.example.ratatouilleapp.View.Authentication.IsignUp;

public class LoginFragmentPresenter {
    private Irepo model;
    Ilogin view;

    public LoginFragmentPresenter(Irepo model, Ilogin view)
    {
        this.model=model;
        this.view=view;
    }

    public void signIn(String email, String password) {

        view.showLoading();
        model.signIn(email, password, new RepoAuthCallback() {
            @Override
            public void onSuccess() {
                view.hideLoading();
                view.onSignInSuccess();
            }

            @Override
            public void onError(Exception e) {
                view.hideLoading();
                view.onSignInFailure(e.getMessage());
            }
        });

//        model.signIn(email, password, new IfireBaseAuth.AuthCallback() {
//            @Override
//            public void onSuccess() {
//                view.hideLoading();
//                view.onSignInSuccess();
//
////                //Shared pref : note try to make in repo
////                SharedPreferences sharedPreferences = ((this.getContext()).getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE));
////                SharedPreferences.Editor editor = sharedPreferences.edit();
////                editor.putBoolean("isSignedIn", true);
////                editor.apply();
//
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                view.hideLoading();
//                view.onSignInFailure(e.getMessage());
//            }
//        });

    }
}
