package com.example.ratatouilleapp.Model.Repo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.ratatouilleapp.Model.Api.Area;
import com.example.ratatouilleapp.Model.Api.Category;
import com.example.ratatouilleapp.Model.Api.Ingredient;
import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.Api.NetworkCallback;
import com.example.ratatouilleapp.Model.Api.NetworkManger;
import com.example.ratatouilleapp.Model.DB.AppDatabase;
import com.example.ratatouilleapp.Model.DB.FavMeal.FavMeal;
import com.example.ratatouilleapp.Model.DB.FavMeal.MealDAO;
import com.example.ratatouilleapp.Model.DB.PlanMeal.Plan;
import com.example.ratatouilleapp.Model.DB.PlanMeal.PlanDAO;
import com.example.ratatouilleapp.Model.Firebase.IfireBaseAuth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;


import java.util.List;

public class Respiratory implements Irepo {
    private Context context;
    private  static Respiratory repo = null;
    private IfireBaseAuth ifireBaseAuth;
    private NetworkManger networkManger;
    private MealDAO mealDAO;
    private LiveData<List<FavMeal>> storedMeal;

//    private FireBaseStoreHandler fireBaseStoreHandler;


  //  private String userEmail;

    private PlanDAO planDAO;
    private LiveData<List<Plan>> storedPlan;



    private Respiratory(Context context , IfireBaseAuth ifireBaseAuth)
    {

        this.context=context;
        this.ifireBaseAuth=ifireBaseAuth;
        this.networkManger = NetworkManger.getInstance();
        AppDatabase db= AppDatabase.getInstance(context.getApplicationContext());
        mealDAO = db.getMealDao();
        storedMeal=mealDAO.getFavMeals(getUserEmail());

        planDAO=db.getPlanDao();
        storedPlan=planDAO.getPlans(getUserEmail());

//        this.fireBaseStoreHandler=FireBaseStoreHandler.getInstance();





    }

    public static Respiratory getInstance(Context context,IfireBaseAuth ifireBaseAuth)
    {
        if (repo==null)
        {
            repo=new Respiratory(context,ifireBaseAuth);
        }
        return repo;
    }

    public String getUserEmail() {


        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("userEmail", null);

    }




    @Override
    public void signIn(String email, String password,  RepoAuthCallback callback) {

        ifireBaseAuth.signIn(email, password, new IfireBaseAuth.AuthCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();


                SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isSignedIn", true);
                editor.putString("userEmail",email);
                editor.apply();

                storedMeal = mealDAO.getFavMeals(email);
                storedPlan = planDAO.getPlans(email);
            }

            @Override
            public void onFailure(Exception e) {
                callback.onError(e);
            }
        });




    }

    @Override
    public void signUp(String email, String password, RepoAuthCallback callback) {

        ifireBaseAuth.signUp(email, password, new IfireBaseAuth.AuthCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();


            }


            @Override
            public void onFailure(Exception e) {

                callback.onError(e);
            }
        });

       // ifireBaseAuth.signUp(email,password,callback);
    }

    @Override
    public void signOut() {

        ifireBaseAuth.signOut();


        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isSignedIn", false);
        editor.remove("userEmail");// This removes the userEmail entry
        editor.clear(); // This clears all data in the preferences
        editor.apply();



    }



    // Network Manger
    @Override
    public void searchMealByName(String mealName, final RepoCallback<List<Meal>> callback) {
        networkManger.searchMealByName(mealName, new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void getMealsByFirstLetter(String letter, final RepoCallback<List<Meal>> callback) {
        networkManger.getMealsByFirstLetter(letter, new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void getMealById(String mealId, final RepoCallback<List<Meal>> callback) {
        networkManger.getMealById(mealId, new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void getRandomMeal(final RepoCallback<List<Meal>> callback) {
        networkManger.getRandomMeal(new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void getMealCategories(final RepoCallback<List<Category>> callback) {
        networkManger.getMealCategories(new NetworkCallback<List<Category>>() {
            @Override
            public void onResponseUpdate(List<Category> categories) {
                callback.onSuccess(categories);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void filterMealsByIngredient(String ingredient, final RepoCallback<List<Meal>> callback) {
        networkManger.filterMealsByIngredient(ingredient, new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void filterMealsByCategory(String category, final RepoCallback<List<Meal>> callback) {
        networkManger.filterMealsByCategory(category, new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void filterMealsByArea(String area, final RepoCallback<List<Meal>> callback) {
        networkManger.filterMealsByArea(area, new NetworkCallback<List<Meal>>() {
            @Override
            public void onResponseUpdate(List<Meal> meals) {
                callback.onSuccess(meals);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);
            }
        });
    }

    @Override
    public void getIngrediants(final RepoCallback<List<Ingredient>> callback)
    {
        networkManger.getAllIngredients(new NetworkCallback<List<Ingredient>>() {
            @Override
            public void onResponseUpdate(List<Ingredient> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);

            }
        });
    }


    @Override
    public void getAreas(final RepoCallback<List<Area>> callback)
    {
        networkManger.getAllAreas(new NetworkCallback<List<Area>>() {
            @Override
            public void onResponseUpdate(List<Area> data) {
                callback.onSuccess(data);
            }

            @Override
            public void onFailure(Throwable throwable) {
                callback.onError(throwable);

            }
        });
    }



    // Database Meal
    public LiveData<List<FavMeal>> getStoredFavMeals()
    {
        return  storedMeal;
    }

    public  void  delet(FavMeal meal)
    {
        //meal.setUserEmail(getUserEmail());

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteMealById(meal.getId(),getUserEmail());
            }
        }).start();
    }


    public void insert(FavMeal meal)
    {
        meal.setUserEmail(getUserEmail());
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertMeal(meal);
            }
        }).start();
    }


    //Database Plan

    public LiveData<List<Plan>> getStoredPlan()
    {
        return storedPlan;
    }

     public   void insertPlan(Plan plan)
    {
       plan.setUserEmail(getUserEmail());
        new Thread(new Runnable() {
            @Override
            public void run() {
                planDAO.insertPlan(plan);
            }
        }).start();
    }

    public void deletPlan(Plan plan) {
        plan.setUserEmail(getUserEmail());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    planDAO.deletPlan(plan);
                } catch (Exception e) {
                    // Log the exception or handle it as needed
                    e.printStackTrace();
                }
            }
        }).start();
    }

//    public void deletPlan(Plan plan)
//    {
//       // plan.setUserEmail(getUserEmail());
//       // plan.getMealId();
//
//        plan.setUserEmail(getUserEmail());
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//               // planDAO.deletPlan(plan.getPlanDate(), plan.getMealId(),plan.getUserEmail());
//                planDAO.deletPlan(plan);
//            }
//        }).start();
//    }



  // FireStore


    // Meals

    public void backupDataToFirestore(RepoCallback<String> callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String userEmail = user.getEmail();

            // Get the list of favorite meals from Room
            getStoredFavMeals().observeForever(new Observer<List<FavMeal>>() {
                @Override
                public void onChanged(List<FavMeal> favMeals) {

                    if (favMeals != null) {
                        for (FavMeal favMeal : favMeals) {
                            // Upload each meal to Firestore
                            db.collection("users").document(userEmail)
                                    .collection("favorites").document(favMeal.getId())
                                    .set(favMeal)
                                    .addOnSuccessListener(aVoid ->callback.onSuccess("Meal backed up successfully"))
                                    .addOnFailureListener(e -> callback.onError(e));
                        }
                    }

                }
            });
        }
    }



    public void restoreDataFromFirestore(RepoCallback<String> callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        if (user != null) {
            String userEmail = user.getEmail();

            db.collection("users").document(userEmail)
                    .collection("favorites").get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                FavMeal favMeal = document.toObject(FavMeal.class);
                                insert(favMeal); // Insert into Room database
                            }
                           callback.onSuccess("Data restored successfully");
                        } else {
                           callback.onError(task.getException());
                        }
                    });
        }
    }



    // Plans


    public void backupPlanDataToFirestore(RepoCallback<String> callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String userEmail = user.getEmail();

            // Get the list of favorite meals from Room
            Observer<List<Plan>> observer = new Observer<List<Plan>>() {
                @Override
                public void onChanged(List<Plan> plans) {
                    if (plans != null) {
                        for (Plan plan : plans) {
                            // Upload each meal to Firestore
                            db.collection("users").document(userEmail)
                                    .collection("Plans").document(String.valueOf(plan.getId()))
                                    .set(plan)
                                    .addOnSuccessListener(aVoid -> callback.onSuccess("Plan backed up successfully"))
                                    .addOnFailureListener(e -> callback.onError(e));
                        }
                    }
                    // Remove the observer after the backup is complete
                    getStoredPlan().removeObserver(this);
                }
            };
            getStoredPlan().observeForever(observer);
        }
    }



//    public void backupPlanDataToFirestore(RepoCallback<String> callback) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (user != null) {
//            String userEmail = user.getEmail();
//
//            // Get the list of favorite meals from Room
//            getStoredPlan().observeForever(new Observer<List<Plan>>() {
//                @Override
//                public void onChanged(List<Plan> plans) {
//
//                    if (plans != null) {
//                        for (Plan plan : plans) {
//                            // Upload each meal to Firestore
//                            db.collection("users").document(userEmail)
//                                    .collection("Plans").document(String.valueOf(plan.getId()))
//                                    .set(plan)
//                                    .addOnSuccessListener(aVoid ->callback.onSuccess("Plan backed up successfully"))
//                                    .addOnFailureListener(e -> callback.onError(e));
//                        }
//                    }
//
//                }
//            });
//        }
//    }

    public void restorePlanDataFromFirestore(RepoCallback<String> callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        if (user != null) {
            String userEmail = user.getEmail();

            db.collection("users").document(userEmail)
                    .collection("Plans").get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Plan plan = document.toObject(Plan.class);
                                insertPlan(plan); // Insert into Room database
                            }
                            callback.onSuccess("Data restored successfully");
                        } else {
                            callback.onError(task.getException());
                        }
                    });
        }
    }






}
