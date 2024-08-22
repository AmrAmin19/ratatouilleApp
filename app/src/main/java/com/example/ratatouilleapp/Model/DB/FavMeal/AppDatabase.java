package com.example.ratatouilleapp.Model.DB.FavMeal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities ={FavMeal.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance=null;
    public abstract MealDAO getMealDao();

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MealDb")
                    .build();
        }
        return instance;
    }
}
