package com.example.ratatouilleapp.Presenter;

import com.example.ratatouilleapp.View.IsplashView;

import java.util.concurrent.TimeUnit;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;


public class SplashPresenter {

    IsplashView isplashView;
    static SplashPresenter splashPresenter;

    private SplashPresenter(IsplashView isplashView)
    {
        this.isplashView=isplashView;
    }

    public static  SplashPresenter getInstance(IsplashView isplashView)
    {
        if (splashPresenter==null)
        {
            splashPresenter=new SplashPresenter(isplashView);
        }
        return splashPresenter;
    }

    public void start(Long time) {
        Observable<Long> doAction = Observable.timer(time, TimeUnit.SECONDS);

        doAction.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((i) -> {
            isplashView.navigateToMainActivity();
        });
    }
}
