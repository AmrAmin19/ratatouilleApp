package com.example.ratatouilleapp.Model.Firebase;



public interface IfireBaseAuth {
    void signIn(String email, String password, AuthCallback callback);
    void signUp(String email, String password, AuthCallback callback);
    void signOut();

    interface AuthCallback {
        void onSuccess();
        void onFailure(Exception e);
    }
}
