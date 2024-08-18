package com.example.ratatouilleapp.Model.Repo;


public interface RepoCallback<T> {
    void onSuccess(T result);
    void onError(Throwable throwable);
}
