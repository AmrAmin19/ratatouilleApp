package com.example.ratatouilleapp.View.Authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.Presenter.SignUpFragmentPresenter;
import com.example.ratatouilleapp.R;

public class SignUpFragment extends Fragment implements IsignUp {


    EditText email;
    EditText password;
    EditText confirmPas;
    Button signUpBtn;
    SignUpFragmentPresenter presenter;


    public SignUpFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter=new SignUpFragmentPresenter( Respiratory.getInstance(this.getContext(),new FireBaseAuthHandler()),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email=view.findViewById(R.id.email);
        password=view.findViewById(R.id.password);
        confirmPas=view.findViewById(R.id.confirm_password);
        signUpBtn=view.findViewById(R.id.sign_in_button);

        signUpBtn.setOnClickListener(v -> {
            String emailS = email.getText().toString();
            String paswordS = password.getText().toString();
            presenter.signUp(emailS, paswordS);
        });




    }




    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this.getContext(), "Sign-up successful!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpFailure(String error) {
        Toast.makeText(this.getContext(), error, Toast.LENGTH_SHORT).show();

    }
}