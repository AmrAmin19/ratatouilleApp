package com.example.ratatouilleapp.View.Favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.DB.FavMeal;
import com.example.ratatouilleapp.R;
import com.example.ratatouilleapp.View.Home.FavHandler;

import com.example.ratatouilleapp.View.Home.MealAdapter;

import java.util.List;

public class FavMealAdapter extends   RecyclerView.Adapter<FavMealAdapter.ViewHolder>{


    private final Context context;
    FavHandler favHandler;
    List<FavMeal> favMeals;







    public FavMealAdapter(Context context, FavHandler favHandler ,List<FavMeal> favMeals)
    {

        this.favMeals=favMeals;
        this.context=context;

        this.favHandler=favHandler;
    }




    public void updateFavMeals(List<FavMeal> favMeals)
    {
        this.favMeals.clear();
        this.favMeals.addAll(favMeals);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.meal_home_item, parent, false);
        return new FavMealAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavMeal meal=favMeals.get(position);
        holder.textView.setText(meal.getName());


        holder.button.setSelected(isMealFavorite(meal.getId()));


       // FavMeal favMeal = new FavMeal(meal.getId(), meal.getName(), meal.getThumbnailUrl());


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (isMealFavorite(meal.getId())) {
                    favHandler.delet(meal);
                    Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
                    holder.button.setSelected(false);
                } else {
                    favHandler.insert(meal);
                    Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
                    holder.button.setSelected(true);
                }

//
//                favHandler.delet(favMeal);
//                Toast.makeText(context, "delet ", Toast.LENGTH_SHORT).show();

//                favHandler.insert(favMeal);
//                Toast.makeText(context, "inser sucsess", Toast.LENGTH_SHORT).show();

            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FavFragmentDirections.ActionFavFragmentToDetailsFragment action =
                        FavFragmentDirections.actionFavFragmentToDetailsFragment(meal.getId());

                Navigation.findNavController(v).navigate(action);

            }
        });

        Glide.with(context)
                .load(meal.getThumbnailUrl())
                .apply(new RequestOptions().override(200, 200))
                .into(holder.imgView);


    }



    @Override
    public int getItemCount() {
        return favMeals.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View layout;
        public  final CardView cardView;
        public final ConstraintLayout constraintLayout;
        public final ImageView imgView;
        public final TextView textView;
        public  final ImageButton button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            constraintLayout = layout.findViewById(R.id.mealLayout);
            cardView=layout.findViewById(R.id.mealCardView);
            imgView = layout.findViewById(R.id.mealImageView);
            textView = layout.findViewById(R.id.mealTitleTextView);
            button=layout.findViewById(R.id.favButton);


        }
    }

    public boolean isMealFavorite(String mealId) {
        for (FavMeal favMeal : favMeals) {
            if (favMeal.getId().equals(mealId)) {
                return true; // The meal is in the favorites list
            }
        }
        return false; // The meal is not in the favorites list
    }
}
