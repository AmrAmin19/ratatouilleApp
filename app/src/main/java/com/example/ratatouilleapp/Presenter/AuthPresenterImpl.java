package com.example.ratatouilleapp.Presenter;

import com.example.ratatouilleapp.Model.Firebase.IfireBaseAuth;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.View.AuthView;

public class AuthPresenterImpl {

   // private IauthModel model;
    private Irepo model;
    private AuthView view;

    public AuthPresenterImpl(AuthView view, Irepo model) {
        this.view = view;
        this.model = model;
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



    public void signUp(String email, String password) {

        view.showLoading();
        model.signUp(email, password, new IfireBaseAuth.AuthCallback() {
            @Override
            public void onSuccess() {
                view.hideLoading();
               // view.onSignUpSuccess();
            }

            @Override
            public void onFailure(Exception e) {
                view.hideLoading();
               // view.onSignUpFailure(e.getMessage());
            }
        });

    }


    public void signOut() {
        model.signOut();
        view.onSignOut();
    }
}
