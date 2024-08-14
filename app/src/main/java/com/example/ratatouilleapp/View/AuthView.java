package com.example.ratatouilleapp.View;

public interface AuthView {
    void showLoading();
    void hideLoading();
    void onSignInSuccess();
    void onSignInFailure(String error);
    void onSignUpSuccess();
    void onSignUpFailure(String error);
    void onSignOut();
}
