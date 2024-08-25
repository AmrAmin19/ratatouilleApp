package com.example.ratatouilleapp.Presenter;

import androidx.lifecycle.Observer;

import com.example.ratatouilleapp.Model.Api.Ingredient;
import com.example.ratatouilleapp.Model.DB.FavMeal.FavMeal;
import com.example.ratatouilleapp.Model.Repo.Irepo;
import com.example.ratatouilleapp.View.Home.SearchView.Isearch;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchPresenter {
    Irepo repo;
    Isearch view;

    private List<String> categoryList=new ArrayList<>();
    private List<String> areaList=new ArrayList<>();
    private List<String> ingredientsLocal=new ArrayList<>();

   public SearchPresenter(Irepo repo,Isearch view)
   {
       this.repo=repo;
       this.view=view;


       //List category

       categoryList.add("Beef");
       categoryList.add("Breakfast");
       categoryList.add("Chicken");
       categoryList.add("Dessert");
       categoryList.add("Goat");
       categoryList.add("Lamb");
       categoryList.add("Miscellaneous");
       categoryList.add("Pasta");
       categoryList.add("Pork");
       categoryList.add("Seafood");
       categoryList.add("Side");
       categoryList.add("Starter");
       categoryList.add("Vegan");
       categoryList.add("Vegetarian");

       //List Area

       areaList.add("American");
       areaList.add("British");
       areaList.add("Canadian");
       areaList.add("Chinese");
       areaList.add("Croatian");
       areaList.add("Dutch");
       areaList.add("Egyptian");
       areaList.add("Filipino");
       areaList.add("French");
       areaList.add("Greek");
       areaList.add("Indian");
       areaList.add("Irish");
       areaList.add("Italian");
       areaList.add("Jamaican");
       areaList.add("Japanese");
       areaList.add("Kenyan");
       areaList.add("Malaysian");
       areaList.add("Mexican");
       areaList.add("Moroccan");
       areaList.add("Polish");
       areaList.add("Portuguese");
       areaList.add("Russian");
       areaList.add("Spanish");
       areaList.add("Thai");
       areaList.add("Tunisian");
       areaList.add("Turkish");
       areaList.add("Ukrainian");
       areaList.add("Unknown");
       areaList.add("Vietnamese");



   }



    public void performSearch(String query, String searchType) {
        switch (searchType) {
            case "name":
                repo.searchMealByName(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> view.showMeals(meals),
                                throwable -> view.showError(throwable.getMessage())
                        );
//               repo.searchMealByName(query, new RepoCallback<List<Meal>>() {
//                   @Override
//                   public void onSuccess(List<Meal> result) {
//                       view.showMeals(result);
//                   }
//
//                   @Override
//                   public void onError(Throwable throwable) {
//                       view.showError("Search name ");
//
//                   }
//               });
                break;

            case "categories":
                repo.filterMealsByCategory(getMatch(query))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> view.showMeals(meals),
                                throwable -> view.showError(throwable.getMessage())
                        );

//               repo.filterMealsByCategory(getMatch(query), new RepoCallback<List<Meal>>() {
//                   @Override
//                   public void onSuccess(List<Meal> result) {
//                       view.showMeals(result);
//                   }
//
//                   @Override
//                   public void onError(Throwable throwable) {
//                       view.showError("Search Category ");
//
//                   }
//               });
                break;

            case "area":
                repo.filterMealsByArea(getMatchArea(query))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> view.showMeals(meals),
                                throwable -> view.showError(throwable.getMessage())
                        );

//              repo.filterMealsByArea(getMatchArea(query), new RepoCallback<List<Meal>>() {
//                  @Override
//                  public void onSuccess(List<Meal> result) {
//                      view.showMeals(result);
//                  }
//
//                  @Override
//                  public void onError(Throwable throwable) {
//
//                      view.showError("Search Area ");
//                  }
//              });
                break;
            case "ingredients":
                repo.filterMealsByIngredient(getMatchIngrediant(query))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                meals -> view.showMeals(meals),
                                throwable -> view.showError(throwable.getMessage())
                        );
        }
    }

    public void getFavList()
    {
        repo.getStoredFavMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favMeals -> {
                            view.ShowMealFavorite(favMeals);
                        }
                );
    }

    public void getIngrediantList()
    {
        repo.getIngrediants()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        ingredients -> {
                           for (Ingredient ingredient:ingredients)
                           {
                               ingredientsLocal.add(ingredient.getName());
                           }
                        }
                );
    }

    public String getMatch(String cat) {
        // Convert the input string to lowercase
        String query = "";
        String lowerCat = cat.toLowerCase();

        // Iterate through the category list
        for (String s : categoryList) {
            String lowerS = s.toLowerCase();

            // Check if the element equals or contains the input string
            if (lowerS.equals(lowerCat) || lowerS.contains(lowerCat)) {
                query = s;
                break; // Exit loop on the first match
            }
        }

        return query.isEmpty() ? null : query;
    }

    public String getMatchArea(String cat) {
        // Convert the input string to lowercase
        String query = "";
        String lowerCat = cat.toLowerCase();

        // Iterate through the category list
        for (String s : areaList) {
            String lowerS = s.toLowerCase();

            // Check if the element equals or contains the input string
            if (lowerS.equals(lowerCat) || lowerS.contains(lowerCat)) {
                query = s;
                break; // Exit loop on the first match
            }
        }

        return query.isEmpty() ? null : query;
    }

    public String getMatchIngrediant(String cat) {
        // Convert the input string to lowercase
        String query = "";
        String lowerCat = cat.toLowerCase();

        // Iterate through the category list
        for (String s : ingredientsLocal) {
            String lowerS = s.toLowerCase();

            // Check if the element equals or contains the input string
            if (lowerS.equals(lowerCat) || lowerS.contains(lowerCat)) {
                query = s;
                break; // Exit loop on the first match
            }
        }

        return query.isEmpty() ? null : query;
    }

    public void insert(FavMeal meal)
    {
        repo.insert(meal);
    }
    public void  delet(FavMeal meal)
    {
        repo.delet(meal);
    }






}
