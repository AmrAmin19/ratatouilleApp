package com.example.ratatouilleapp.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ratatouilleapp.Presenter.SplashPresenter;
import com.example.ratatouilleapp.R;

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
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
