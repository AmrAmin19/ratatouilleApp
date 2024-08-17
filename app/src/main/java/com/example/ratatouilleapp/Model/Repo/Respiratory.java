package com.example.ratatouilleapp.Model.Repo;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ratatouilleapp.Model.Firebase.IfireBaseAuth;

public class Respiratory implements Irepo{
    private Context context;
    private  static Respiratory repo = null;
    private IfireBaseAuth ifireBaseAuth;

    private Respiratory(Context context , IfireBaseAuth ifireBaseAuth)
    {
        this.context=context;
        this.ifireBaseAuth=ifireBaseAuth;

    }

    public static Respiratory getInstance(Context context,IfireBaseAuth ifireBaseAuth)
    {
        if (repo==null)
        {
            repo=new Respiratory(context,ifireBaseAuth);
        }
        return repo;
    }


    @Override
    public void signIn(String email, String password, IfireBaseAuth.AuthCallback callback) {

        ifireBaseAuth.signIn(email,password,callback);

//        SharedPreferences sharedPreferences = ((context).getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE));
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("isSignedIn", true);
//        editor.apply();


    }

    @Override
    public void signUp(String email, String password, IfireBaseAuth.AuthCallback callback) {

        ifireBaseAuth.signUp(email,password,callback);
    }

    @Override
    public void signOut() {

        ifireBaseAuth.signOut();

        SharedPreferences sharedPreferences = (context).getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isSignedIn", false);
        editor.apply();

    }
}
