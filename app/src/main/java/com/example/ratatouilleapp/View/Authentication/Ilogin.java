package com.example.ratatouilleapp.View.Authentication;

public interface Ilogin {
    void showLoading();
    void hideLoading();
    void onSignInSuccess();
    void onSignInFailure(String error);
}
