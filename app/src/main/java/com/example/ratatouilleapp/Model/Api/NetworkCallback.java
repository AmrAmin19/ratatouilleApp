package com.example.ratatouilleapp.Model.Api;


public interface NetworkCallback<T> {
    void onResponseUpdate(T data);
    void onFailure(Throwable throwable);
}