package com.example.ratatouilleapp.View.Authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.ratatouilleapp.R;


public class AuthFragment extends Fragment {

    //TextView authText;
    ImageView faceBtn;
    ImageView googleBtn;
    Button signUpNtn;
    TextView login;

    public AuthFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_auth, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        faceBtn=view.findViewById(R.id.facebookImage);
        googleBtn=view.findViewById(R.id.googleImage);
        signUpNtn=view.findViewById(R.id.button);
        login=view.findViewById(R.id.textView2);



        signUpNtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_authFragment_to_signUpFragment);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_authFragment_to_logInFragment);
            }
        });

    }
}