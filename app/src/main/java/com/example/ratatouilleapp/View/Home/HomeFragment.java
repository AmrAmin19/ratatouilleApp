package com.example.ratatouilleapp.View.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.Model.Repo.RepoCallback;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.R;

import java.util.List;


public class HomeFragment extends Fragment {




public HomeFragment(){}




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Irepo repo= Respiratory.getInstance(this.getContext(),new FireBaseAuthHandler());

        repo.searchMealByName("Arrabiata", new RepoCallback<List<Meal>>() {
            @Override
            public void onSuccess(List<Meal> result) {
                // Update the view with the result
                Log.d("TAG", "onSuccess: " + result.get(0).getIngredient1());
            }

            @Override
            public void onError(Throwable throwable) {
                // Handle the error
                Log.d("TAG", "onError: ");
            }
        });
    }
}