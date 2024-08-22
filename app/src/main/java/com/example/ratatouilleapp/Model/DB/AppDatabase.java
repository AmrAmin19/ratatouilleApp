package com.example.ratatouilleapp.Model.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ratatouilleapp.Model.DB.FavMeal.FavMeal;
import com.example.ratatouilleapp.Model.DB.FavMeal.MealDAO;
import com.example.ratatouilleapp.Model.DB.PlanMeal.Plan;
import com.example.ratatouilleapp.Model.DB.PlanMeal.PlanDAO;

@Database(entities = {FavMeal.class, Plan.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance=null;
    public abstract MealDAO getMealDao();
    public abstract PlanDAO getPlanDao();

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MealDb")
                    .build();
        }
        return instance;
    }
}
