package com.example.ratatouilleapp.Presenter;

import com.example.ratatouilleapp.Model.Firebase.IfireBaseAuth;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.Model.Repo.RepoAuthCallback;
import com.example.ratatouilleapp.View.Authentication.IsignUp;

public class SignUpFragmentPresenter {
    private Irepo model;
    IsignUp view;

    public SignUpFragmentPresenter(Irepo model, IsignUp view)
    {
        this.model=model;
        this.view=view;
    }

    public void signUp(String email, String password) {

        view.showLoading();
        model.signUp(email, password, new RepoAuthCallback() {
            @Override
            public void onSuccess() {
                view.hideLoading();
                view.onSignUpSuccess();
            }

            @Override
            public void onError(Exception e) {
                view.hideLoading();
                view.onSignUpFailure(e.getMessage());
            }
        });
//        model.signUp(email, password, new IfireBaseAuth.AuthCallback() {
//            @Override
//            public void onSuccess() {
//                view.hideLoading();
//                view.onSignUpSuccess();
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                view.hideLoading();
//                view.onSignUpFailure(e.getMessage());
//            }
//        });

    }
}
