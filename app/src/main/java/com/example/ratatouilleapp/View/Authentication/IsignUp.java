package com.example.ratatouilleapp.View.Authentication;

public interface IsignUp {
    void onSignUpSuccess();
    void onSignUpFailure(String error);
    void showLoading();
    void hideLoading();
}
