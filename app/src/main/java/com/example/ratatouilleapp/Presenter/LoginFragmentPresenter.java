package com.example.ratatouilleapp.Presenter;

import com.example.ratatouilleapp.Model.Firebase.IfireBaseAuth;
import com.example.ratatouilleapp.Model.Repo.Irepo;
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
        model.signIn(email, password, new IfireBaseAuth.AuthCallback() {
            @Override
            public void onSuccess() {
                view.hideLoading();
                view.onSignInSuccess();
            }

            @Override
            public void onFailure(Exception e) {
                view.hideLoading();
                view.onSignInFailure(e.getMessage());
            }
        });

    }
}
