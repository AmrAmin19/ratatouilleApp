package com.example.ratatouilleapp.View.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratatouilleapp.Model.Api.Area;
import com.example.ratatouilleapp.Model.Api.Category;
import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.Presenter.HomePresenter;
import com.example.ratatouilleapp.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements Ihome {

    private RecyclerView categoriesView;
    private  RecyclerView mealView;
    private RecyclerView areaView;
    private  RecyclerView mealByAreaView;
    private CategoriesAdapter categoriesAdapter;
    private HomePresenter presenter;
    private MealAdapter mealAdapter;
    private MealAdapter mealAdapterArea;
    private  AreaAdapter areaAdapter;


    TextView catText;





public HomeFragment(){}




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoriesView = view.findViewById(R.id.catRecycle);
        mealView=view.findViewById(R.id.mealRecycle);
        areaView=view.findViewById(R.id.AreaRycycle);
        mealByAreaView=view.findViewById(R.id.mealByArea);
        //catText=view.findViewById(R.id.catTitleText);



        presenter=new HomePresenter(this,Respiratory.getInstance(this.getContext(),new FireBaseAuthHandler()));

        categoriesAdapter = new CategoriesAdapter(new ArrayList<>(), this.getContext(),this);
        mealAdapter=new MealAdapter(this.getContext(),new ArrayList<>());
        areaAdapter=new AreaAdapter(this.getContext(),new ArrayList<>(),this);
        mealAdapterArea=new MealAdapter(this.getContext(),new ArrayList<>());



        categoriesView.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL, false));
        categoriesView.setAdapter(categoriesAdapter);

        mealView.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL, false));
        mealView.setAdapter(mealAdapter);

        areaView.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL, false));
        areaView.setAdapter(areaAdapter);

        mealByAreaView.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL, false));
        mealByAreaView.setAdapter(mealAdapterArea);

        presenter.getMealCategories();
        presenter.getMealByCategory("Beef");

        presenter.getAreas();

        presenter.filterByArea("dutch");

      //  updateTextView("Beef");


    }

    @Override
    public void showCategories(List<Category> categories) {
        categoriesAdapter.updateProducts(categories);
    }

    @Override
    public void showError(String message) {

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMeals(List<Meal> meals) {
    mealAdapter.updateMeals(meals);

    }

    @Override
    public void showAreas(List<Area> areas) {
        areaAdapter.updateAreas(areas);
    }

    @Override
    public void showMealsByArea(List<Meal> meals) {
        mealAdapterArea.updateMeals(meals);
    }


    //    public void updateTextView(String newText) {
//        if (catText != null) {
//            catText.setText(newText);
//        }
//    }
    public void updateListByCategory(String category)
    {
       presenter.getMealByCategory(category);
    }
    public void updateListByArea(String area){presenter.filterByArea(area);};


}