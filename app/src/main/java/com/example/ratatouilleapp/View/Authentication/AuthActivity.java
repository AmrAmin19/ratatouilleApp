package com.example.ratatouilleapp.View.Authentication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.ratatouilleapp.R;


public class AuthActivity extends AppCompatActivity  {

//    private AuthPresenterImpl presenter;
//    private ProgressBar progressBar;
//    private EditText emailEditText;
//    private EditText passwordEditText;
//    private Button signInButton;
//    private Button signUpButton;
//    private Button signOutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auth);
//
//        progressBar = findViewById(R.id.progressBar);
//        emailEditText = findViewById(R.id.emailEditText);
//        passwordEditText = findViewById(R.id.passwordEditText);
//        signInButton = findViewById(R.id.signInButton);
//        signUpButton = findViewById(R.id.signUpButton);
//        signOutButton = findViewById(R.id.signOutButton);



//        presenter = new AuthPresenterImpl(this,Respiratory.getInstance(this,new FireBaseAuthHandler()));
//
//        signInButton.setOnClickListener(v -> {
//            String email = emailEditText.getText().toString();
//            String password = passwordEditText.getText().toString();
//            presenter.signIn(email, password);
//        });

//        signUpButton.setOnClickListener(v -> {
//            String email = emailEditText.getText().toString();
//            String password = passwordEditText.getText().toString();
//            presenter.signUp(email, password);
//        });

        //    signOutButton.setOnClickListener(v -> presenter.signOut());

        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this,navController);
    }


}
