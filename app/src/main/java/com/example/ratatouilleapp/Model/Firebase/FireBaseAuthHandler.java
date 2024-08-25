package com.example.ratatouilleapp.Model.Firebase;

import android.util.Log;

import com.example.ratatouilleapp.Model.Repo.RepoCallback;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

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

    public String getCurrentUser()
    {
        return mAuth.getCurrentUser().getEmail();
    }


    public void signInUsingGmailAccount(String idToken, AuthCallback callback) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

//               String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
//               // GoogleSignInAccount googleSignInAccount= GoogleSignIn.getLastSignedInAccount(context);
//
//                SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean("isSignedIn", true);
//                editor.putString("userEmail",email );
//                editor.apply();

                callback.onSuccess();

            } else {
                callback.onFailure(task.getException());
            }
        });
    }

//    public void linkWithGoogle(GoogleSignInAccount googleAccount, IfireBaseAuth.AuthCallback callback) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(googleAccount.getIdToken(), null);
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//
//        if (currentUser != null) {
//            currentUser.linkWithCredential(credential)
//                    .addOnCompleteListener(task -> {
//                        if (task.isSuccessful()) {
//                            Log.d("TAG", "Account linked with Google");
//                            callback.onSuccess();
//                        } else {
//                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Unknown error";
//                            Log.d("TAG", "Linking failed: " + errorMessage);
//                            callback.onFailure(task.getException());
//                        }
//                    });
//        } else {
//            Log.d("TAG", "No current user to link with Google");
//            callback.onFailure(new Exception("No current user"));
//        }
//    }
}
