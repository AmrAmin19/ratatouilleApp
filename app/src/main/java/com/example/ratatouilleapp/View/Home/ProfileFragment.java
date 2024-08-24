package com.example.ratatouilleapp.View.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.R;
import com.example.ratatouilleapp.View.Authentication.AuthActivity;
import com.example.ratatouilleapp.View.MainActivity;


public class ProfileFragment extends Fragment {

Button button;
    private Irepo model;


    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model= Respiratory.getInstance(this.getContext(),new FireBaseAuthHandler());
        button=view.findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.signOut();


                Intent intent = new Intent(getActivity(), AuthActivity.class);
                startActivity(intent);
                getActivity().finish();



                Toast.makeText(getActivity(), "Sign out sucsess", Toast.LENGTH_SHORT).show();

            }
        });

    }
}