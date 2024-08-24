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
import com.example.ratatouilleapp.Model.Repo.RepoCallback;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.R;
import com.example.ratatouilleapp.View.Authentication.AuthActivity;


public class ProfileFragment extends Fragment {

Button button;
Button buttonBackUp;
    private Irepo model;
    private Button restoreBtn;

    private Button backupPlan;
    private Button restorePlan;


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
        buttonBackUp=view.findViewById(R.id.button2);
        restoreBtn=view.findViewById(R.id.button3);

        backupPlan=view.findViewById(R.id.button5);
        restorePlan=view.findViewById(R.id.button6);

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

        buttonBackUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.backupDataToFirestore(new RepoCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable throwable) {

                        Toast.makeText(getContext(), throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        restoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.restoreDataFromFirestore(new RepoCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getContext(), "Restored", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable throwable) {

                        Toast.makeText(getContext(), throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

        backupPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.backupPlanDataToFirestore(new RepoCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Toast.makeText(getContext(), throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        restorePlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.restorePlanDataFromFirestore(new RepoCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Toast.makeText(getContext(), throwable.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}