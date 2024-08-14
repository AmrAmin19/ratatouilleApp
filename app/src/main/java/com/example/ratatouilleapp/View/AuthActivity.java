package com.example.ratatouilleapp.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.Presenter.AuthPresenterImpl;
import com.example.ratatouilleapp.R;

public class AuthActivity extends AppCompatActivity implements AuthView{

    private AuthPresenterImpl presenter;
    private ProgressBar progressBar;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signInButton;
    private Button signUpButton;
    private Button signOutButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auth);

        progressBar = findViewById(R.id.progressBar);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);
        signOutButton = findViewById(R.id.signOutButton);



        presenter = new AuthPresenterImpl(this,Respiratory.getInstance(this,new FireBaseAuthHandler()));

        signInButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            presenter.signIn(email, password);
        });

        signUpButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            presenter.signUp(email, password);
        });

        signOutButton.setOnClickListener(v -> presenter.signOut());
    }

    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "Sign-In successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignInFailure(String error) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {

        Toast.makeText(this, "Sign-up successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpFailure(String error) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignOut() {

    }
}
