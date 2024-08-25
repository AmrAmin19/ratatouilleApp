package com.example.ratatouilleapp.Model.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ratatouilleapp.Model.DB.FavMeal.FavMeal;
import com.example.ratatouilleapp.Model.DB.FavMeal.MealDAO;
import com.example.ratatouilleapp.Model.DB.PlanMeal.Plan;
import com.example.ratatouilleapp.Model.DB.PlanMeal.PlanDAO;

@Database(entities = {FavMeal.class, Plan.class}, version =5)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance=null;
    public abstract MealDAO getMealDao();
    public abstract PlanDAO getPlanDao();

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE meals_tabel ADD COLUMN userEmail TEXT");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // 1. Create a new table with the new schema
            database.execSQL("CREATE TABLE meals_tabel_new (" +
                    "UniqueId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "id TEXT , " +
                    "name TEXT, " +
                    "thumbnailUrl TEXT, " +
                    "userEmail TEXT)");

            // 2. Copy data from the old table to the new table
            database.execSQL("INSERT INTO meals_tabel_new (id, name, thumbnailUrl, userEmail) " +
                    "SELECT id, name, thumbnailUrl, userEmail FROM meals_tabel");

            // 3. Drop the old table
            database.execSQL("DROP TABLE meals_tabel");

            // 4. Rename the new table to the old table name
            database.execSQL("ALTER TABLE meals_tabel_new RENAME TO meals_tabel");
        }
    };

    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE meal_plans ADD COLUMN userEmail TEXT");
        }
    };

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MealDb")
                    .addMigrations(MIGRATION_2_3,MIGRATION_3_4,MIGRATION_4_5)
                    .build();
        }
        return instance;
    }
}
