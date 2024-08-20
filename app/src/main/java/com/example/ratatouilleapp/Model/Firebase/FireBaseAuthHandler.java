package com.example.ratatouilleapp.Model.Firebase;

import com.google.firebase.auth.FirebaseAuth;

public class FireBaseAuthHandler implements IfireBaseAuth{
    private FirebaseAuth mAuth;

    public FireBaseAuthHandler()
    {
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void signIn(String email, String password, AuthCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess();
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    @Override
    public void signUp(String email, String password, AuthCallback callback) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess();
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    @Override
    public void signOut() {

        mAuth.signOut();
    }
}
