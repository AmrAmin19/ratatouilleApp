package com.example.ratatouilleapp.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Irepo model;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //  BottomAppBar bottomAppBar=findViewById(R.id.)
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Set up navigation with the BottomNavigationView
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


        model=Respiratory.getInstance(this,new FireBaseAuthHandler());
        button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.signOut();



                Toast.makeText(MainActivity.this, "Sign out sucsess", Toast.LENGTH_SHORT).show();

            }
        });


    }
}