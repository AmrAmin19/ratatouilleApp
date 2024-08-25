package com.example.ratatouilleapp.View.Home.SearchView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.DB.FavMeal.FavMeal;
import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.Presenter.SearchPresenter;
import com.example.ratatouilleapp.R;
import com.example.ratatouilleapp.View.Home.FavHandler;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class SearchFragment extends Fragment implements Isearch , FavHandler {

    private RecyclerView searchMeal;
    private ChipGroup chipGroup;
    private SearchAdapter searchMealAdabter;

    private SearchView searchEditText;
    private Disposable disposable;
    private CompositeDisposable disposables = new CompositeDisposable();
    private PublishSubject<String> searchSubject = PublishSubject.create();


//    private List<String> categoryList=new ArrayList<>();
//    private List<String> areaList=new ArrayList<>();

    SearchPresenter presenter;

    Boolean isFav=false;



    public SearchFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchMeal=view.findViewById(R.id.recycleSearch);
        chipGroup=view.findViewById(R.id.chipGroup);
        searchEditText=view.findViewById(R.id.searchView);
        //fix

        searchMealAdabter=new SearchAdapter(this.getContext(),new ArrayList<>(),this  , new ArrayList<>());

//        searchMeal.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false));
        searchMeal.setAdapter(searchMealAdabter);

        presenter=new SearchPresenter( Respiratory.getInstance(this.getContext(),new FireBaseAuthHandler()),this);
        presenter.getFavList();
        presenter.getIngrediantList();



        updateChipsItems();

        // Search test
       setupSearchView(searchEditText, searchSubject,"name");
//        setupSearchView(searchEditText, searchSubject);



        disposables.add(searchSubject
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(query -> !query.isEmpty())
                .subscribe(query -> {
                    String[] queryParts = query.split(":", 2);
                    if (queryParts.length == 2) {
                        Log.d("Amr obs", "onViewCreated: ");

                       presenter.performSearch(queryParts[0], queryParts[1]);
                    }
                }, throwable -> Log.e("SearchError", "Error processing search", throwable)));

    }




    public void setupSearchView(SearchView searchView, PublishSubject<String> searchSubject, String searchType) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchSubject.onNext(query.trim() + ":" + searchType);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchSubject.onNext(newText.trim() + ":" + searchType);
                //Log.d("onTextAmr", newText.trim());
                return true;
            }
        });
    }


    private void updateChipsItems() {
        for (int i=0 ; i < chipGroup.getChildCount(); i++)
        {
            Chip chip = (Chip) chipGroup.getChildAt(i);

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if(isChecked)
                    {
                        searchMealAdabter.updateMeals(new ArrayList<>());

                        String selectedType = chip.getText().toString().toLowerCase();
                        Log.d("AmrChip", chip.getText().toString().toLowerCase());

                        setupSearchView(searchEditText, searchSubject,selectedType);
//                        setupSearchView(searchEditText, searchSubject);




                    }
                }
            });
        }
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        disposables.clear();
    }


    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMeals(List<Meal> meals) {
        if (meals != null){
            searchMealAdabter.updateMeals(meals);
        }
        else {
            Toast.makeText(getActivity(), "no result found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void ShowMealFavorite(List<FavMeal> favMeals) {
        searchMealAdabter.updateFavMeals(favMeals);
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