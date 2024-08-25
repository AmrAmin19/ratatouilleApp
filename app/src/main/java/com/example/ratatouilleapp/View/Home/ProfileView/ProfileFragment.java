package com.example.ratatouilleapp.View.Home.ProfileView;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.Model.Repo.RepoCallback;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.Presenter.ProfilePresenter;
import com.example.ratatouilleapp.R;
import com.example.ratatouilleapp.View.Authentication.AuthActivity;


public class ProfileFragment extends Fragment implements Iprofile {



    private ImageButton backup;
    private ImageButton restore;
    private Button logout;
    private TextView emailView;

    ProfilePresenter presenter;


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

        presenter=new ProfilePresenter(Respiratory.getInstance(this.getContext(),new FireBaseAuthHandler()),this);

        LottieAnimationView animationView =view.findViewById(R.id.lottieAnimationView);
        animationView.setAnimation(R.raw.profile_anime);
        animationView.playAnimation();
        emailView=view.findViewById(R.id.emailView);

        backup=view.findViewById(R.id.backUp);
        restore=view.findViewById(R.id.restore);
        logout=view.findViewById(R.id.button4);

        emailView.setText(presenter.getuserEmail());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.logout();

                Intent intent = new Intent(getActivity(), AuthActivity.class);
                startActivity(intent);
                getActivity().finish();



                Toast.makeText(getActivity(), "Sign out sucsess", Toast.LENGTH_SHORT).show();

            }
        });

        backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.backupPlans();
                presenter.backUpMeals();

            }
        });

        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.restoreMeals();
                presenter.restorePlans();

            }
        });



    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(String error) {

    }
}