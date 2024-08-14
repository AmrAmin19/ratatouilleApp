package com.example.ratatouilleapp.Model.Repo;

import com.example.ratatouilleapp.Model.Firebase.IfireBaseAuth;

public interface Irepo {
    void signIn(String email, String password, IfireBaseAuth.AuthCallback callback);
    void signUp(String email, String password, IfireBaseAuth.AuthCallback callback);
    void signOut();
}
