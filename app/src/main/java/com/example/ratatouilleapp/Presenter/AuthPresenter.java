package com.example.ratatouilleapp.Presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.Model.Repo.RepoAuthCallback;
import com.example.ratatouilleapp.Model.Repo.RepoCallback;
import com.example.ratatouilleapp.View.Authentication.Iauth;
import com.example.ratatouilleapp.View.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class AuthPresenter {


    Irepo repo;
    Iauth view;

   public AuthPresenter(Irepo repo,Iauth view)
   {
       this.repo=repo;
       this.view=view;
   }




   public void signInWithGoogle(String idToken)
   {
       repo.signInUsingGmailAccount(idToken, new RepoCallback<String>() {
           @Override
           public void onSuccess(String result) {



               view.onSignInSuccess();
           }

           @Override
           public void onError(Throwable throwable) {
               view.onSignInFailure(throwable.toString());
           }
       });
   }

//    public void linkGoogle(GoogleSignInAccount googleSignInAccount)
//    {
//        repo.linkWithGoogle(googleSignInAccount, new RepoAuthCallback() {
//            @Override
//            public void onSuccess() {
//                view.onSignInSuccess();
//            }
//
//            @Override
//            public void onError(Exception e) {
//                view.onSignInFailure(e.toString());
//            }
//        });
//    }


//    public void signInUsingGmailAccount(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
//                getActivity().finish();
//
//                Toast.makeText(this.getContext(), "Sucsses google", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this.getContext(), "fail google", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
