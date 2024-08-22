package com.example.ratatouilleapp.View.Home.FavoriteView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ratatouilleapp.Model.DB.FavMeal.FavMeal;
import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.Presenter.FavPresenter;
import com.example.ratatouilleapp.R;
import com.example.ratatouilleapp.View.Home.FavHandler;

import java.util.ArrayList;
import java.util.List;


public class FavFragment extends Fragment implements  Ifav, FavHandler {

    private RecyclerView favRecycle;
    FavMealAdapter favMealAdapter;
    FavPresenter presenter;


    public FavFragment() {
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
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favRecycle=view.findViewById(R.id.recycleFav);

        presenter=new FavPresenter( Respiratory.getInstance(this.getContext(),new FireBaseAuthHandler()),this);


        favMealAdapter=new FavMealAdapter(this.getContext(),this , new ArrayList<>());
        favRecycle.setAdapter(favMealAdapter);

        presenter.getFavList();

    }

    @Override
    public void ShowMealFavorite(List<FavMeal> favMeals) {
        favMealAdapter.updateFavMeals(favMeals);
    }

    @Override
    public void insert(FavMeal meal) {
        presenter.insert(meal);
    }

    @Override
    public void delet(FavMeal meal) {
        presenter.delet(meal);
    }
}