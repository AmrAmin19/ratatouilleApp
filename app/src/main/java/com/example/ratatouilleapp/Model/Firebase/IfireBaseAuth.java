package com.example.ratatouilleapp.Model.Firebase;


import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface IfireBaseAuth {
    void signIn(String email, String password, AuthCallback callback);
    void signUp(String email, String password, AuthCallback callback);
    void signOut();


   void signInUsingGmailAccount(String idToken, AuthCallback callback);

     String getCurrentUser();

    interface AuthCallback {
        void onSuccess();
        void onFailure(Exception e);
    }
}
