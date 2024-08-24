package com.example.ratatouilleapp.View.Splash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ratatouilleapp.Presenter.SplashPresenter;
import com.example.ratatouilleapp.R;
import com.example.ratatouilleapp.View.Authentication.AuthActivity;
import com.example.ratatouilleapp.View.MainActivity;

public class SplashActivity extends AppCompatActivity implements IsplashView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);




        LottieAnimationView animationView = findViewById(R.id.lottieAnimationView);
        animationView.setAnimation(R.raw.splash_anim);
        animationView.playAnimation();

        SplashPresenter splashPresenter=SplashPresenter.getInstance(this);
        splashPresenter.start(3L);


    }

    @Override
    public void navigateToMainActivity() {
//        Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
//        startActivity(intent);
//        finish();
//
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        boolean isSignedIn = sharedPreferences.getBoolean("isSignedIn", false);


        if (isSignedIn) {
            // Navigate to HomeActivity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
        } else {
            // Navigate to AuthActivity
            Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
        }
    }
}
