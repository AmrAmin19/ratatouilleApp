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

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Respiratory implements Irepo {
    private Context context;
    private  static Respiratory repo = null;
    private IfireBaseAuth ifireBaseAuth;
    private NetworkManger networkManger;
    private MealDAO mealDAO;
    private Flowable<List<FavMeal>> storedMeal;

//    private FireBaseStoreHandler fireBaseStoreHandler;


  //  private String userEmail;

    private PlanDAO planDAO;
    private Flowable<List<Plan>> storedPlan;



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
    public Single<List<Meal>> searchMealByName(String mealName) {
        return networkManger.searchMealByName(mealName);
    }

    @Override
    public Single<List<Meal>> getMealsByFirstLetter(String letter) {
        return networkManger.getMealsByFirstLetter(letter);
    }

    @Override
    public Single<List<Meal>> getMealById(String mealId) {
      return   networkManger.getMealById(mealId);

    }

    @Override
    public Single<List<Meal>> getRandomMeal() {
        return networkManger.getRandomMeal();

    }

    @Override
    public Single<List<Category>> getMealCategories() {
       return networkManger.getMealCategories();
    }

    @Override
    public Single<List<Meal>> filterMealsByIngredient(String ingredient) {
       return networkManger.filterMealsByIngredient(ingredient);
    }

    @Override
    public Single<List<Meal>> filterMealsByCategory(String category) {
        return networkManger.filterMealsByCategory(category);
    }

    @Override
    public Single<List<Meal>> filterMealsByArea(String area) {
        return networkManger.filterMealsByArea(area);
    }

    @Override
    public Single<List<Ingredient>> getIngrediants()
    {
        return networkManger.getAllIngredients();
    }


    @Override
    public Single<List<Area>> getAreas()
    {
        return networkManger.getAllAreas();
    }



    // Database Meal
    public Flowable<List<FavMeal>> getStoredFavMeals()
    {

        return  storedMeal;
    }

    public  void  delet(FavMeal meal)
    {
        mealDAO.deleteMealById(meal.getId(),getUserEmail())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Log.d("TestDatabase", "Delete: ");
                }, throwable -> {
                    Log.d("TestDatabase", "Delete: "+"failuer");
                });
    }


    public void insert(FavMeal meal)
    {
        meal.setUserEmail(getUserEmail());
        mealDAO.insertMeal(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Log.d("TestDatabase", "insert: ");
                }, throwable -> {
                    Log.d("TestDatabase", "insert: "+"failuer");
                });
    }


    //Database Plan

    public Flowable<List<Plan>> getStoredPlan()
    {

        return storedPlan;
    }

     public   void insertPlan(Plan plan)
    {
       plan.setUserEmail(getUserEmail());
       planDAO.insertPlan(plan)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(() -> {
                   Log.d("TestDatabase", "insert: Plan ");
               }, throwable -> {
                   Log.d("TestDatabase", "insert: Plan "+"failuer");
               });

    }

    public void deletPlan(Plan plan) {
        plan.setUserEmail(getUserEmail());
        planDAO.deletPlan(plan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Log.d("TestDatabase", "Delete plan ");
                }, throwable -> {
                    Log.d("TestDatabase", "Delete : Plan "+"failuer");
                });

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
            getStoredFavMeals()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            favMeals -> {
                                if (favMeals != null) {
                                    for (FavMeal favMeal : favMeals) {
                                        // Upload each meal to Firestore
                                        db.collection("users").document(userEmail)
                                                .collection("favorites").document(favMeal.getId())
                                                .set(favMeal)
                                                .addOnSuccessListener(aVoid -> Log.d("backupAmr", "backupDataToFirestore: meal "))
                                                .addOnFailureListener(e -> Log.d("backupAmr", "backupDataToFirestore: meal "+"faliur"));
//                                                .addOnSuccessListener(aVoid ->callback.onSuccess("Meal backed up successfully"))
//                                                .addOnFailureListener(e -> callback.onError(e));
                                    }
                                }
                            }
                            // Add showError to the view interface if not already present
                    );
//            getStoredFavMeals().observeForever(new Observer<List<FavMeal>>() {
//                @Override
//                public void onChanged(List<FavMeal> favMeals) {
//
//                    if (favMeals != null) {
//                        for (FavMeal favMeal : favMeals) {
//                            // Upload each meal to Firestore
//                            db.collection("users").document(userEmail)
//                                    .collection("favorites").document(favMeal.getId())
//                                    .set(favMeal)
//                                    .addOnSuccessListener(aVoid ->callback.onSuccess("Meal backed up successfully"))
//                                    .addOnFailureListener(e -> callback.onError(e));
//                        }
//                    }
//
//                }
//            });
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
                            Log.d("backupAmr", "restoreDataFromFirestore: meal ");
                           //callback.onSuccess("Data restored successfully");
                        } else {
                            Log.d("backupAmr", "restoreDataFromFirestore: meal " +"faliur");
                           //callback.onError(task.getException());
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

            getStoredPlan()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            plans -> {
                                if (plans != null) {
                        for (Plan plan : plans) {
                            // Upload each meal to Firestore
                            db.collection("users").document(userEmail)
                                    .collection("Plans").document(String.valueOf(plan.getId()))
                                    .set(plan)
                                    .addOnSuccessListener(aVoid -> Log.d("backupAmr", "backupPlanDataToFirestore: plan "))
                                    .addOnFailureListener(e -> Log.d("backupAmr", "backupPlanDataToFirestore: plan "+"faliur"));
//                                    .addOnSuccessListener(aVoid -> callback.onSuccess("Plan backed up successfully"))
//                                    .addOnFailureListener(e -> callback.onError(e));
                        }
                    }

                            }
                    );

            // Get the list of favorite meals from Room
//            Observer<List<Plan>> observer = new Observer<List<Plan>>() {
//                @Override
//                public void onChanged(List<Plan> plans) {
////                    if (plans != null) {
////                        for (Plan plan : plans) {
////                            // Upload each meal to Firestore
////                            db.collection("users").document(userEmail)
////                                    .collection("Plans").document(String.valueOf(plan.getId()))
////                                    .set(plan)
////                                    .addOnSuccessListener(aVoid -> callback.onSuccess("Plan backed up successfully"))
////                                    .addOnFailureListener(e -> callback.onError(e));
////                        }
////                    }
//                    // Remove the observer after the backup is complete
//                  //  getStoredPlan().removeObserver(this);
//                }
//            };
//           // getStoredPlan().observeForever(observer);
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
                            Log.d("backupAmr", "restorePlanDataFromFirestore: plan ");
                           // callback.onSuccess("Data restored successfully");
                        } else {
                           // callback.onError(task.getException());
                            Log.d("backupAmr", "restorePlanDataFromFirestore: plan "+"failuer");
                        }
                    });
        }
    }






}
