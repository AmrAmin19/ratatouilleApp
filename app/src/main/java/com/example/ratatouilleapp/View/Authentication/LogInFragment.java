package com.example.ratatouilleapp.View.Authentication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.Presenter.LoginFragmentPresenter;
import com.example.ratatouilleapp.Presenter.SignUpFragmentPresenter;
import com.example.ratatouilleapp.R;
import com.example.ratatouilleapp.View.MainActivity;

public class LogInFragment extends Fragment implements Ilogin {

    EditText email;
    EditText password;
    Button login;
    private ProgressBar progressBar;

    LoginFragmentPresenter presenter;


    public LogInFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter=new LoginFragmentPresenter( Respiratory.getInstance(this.getContext(),new FireBaseAuthHandler()),this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email=view.findViewById(R.id.email);
        password=view.findViewById(R.id.password);
        login=view.findViewById(R.id.log_in_button);
        progressBar = view.findViewById(R.id.progressBar);

        login.setOnClickListener(v -> {
            String emailS = email.getText().toString();
            String passwordS = password.getText().toString();
            presenter.signIn(emailS, passwordS);
            email.setText("");
            password.setText("");

        });



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

        //Shared pref : note try to make in repo
//        SharedPreferences sharedPreferences = ((this.getContext()).getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE));
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("isSignedIn", true);
//        editor.apply();

        Intent intent = new Intent(this.getContext(), MainActivity.class);
        startActivity(intent);
        this.getActivity().finish();

        Toast.makeText(this.getContext(), "Sign-In successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignInFailure(String error) {
        Toast.makeText(this.getContext(), error, Toast.LENGTH_SHORT).show();

    }
}