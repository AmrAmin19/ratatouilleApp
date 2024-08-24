package com.example.ratatouilleapp.View.Home.SearchView;

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
import com.example.ratatouilleapp.Model.DB.FavMeal.FavMeal;
import com.example.ratatouilleapp.R;
import com.example.ratatouilleapp.View.Home.FavHandler;

import java.util.List;

public class SearchAdapter extends  RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final Context context;
    private final List<Meal> meals;
    FavHandler favHandler;
    List<FavMeal> favMeals;







    public SearchAdapter(Context context,List<Meal> meals, FavHandler favHandler ,List<FavMeal> favMeals)
    {

        this.favMeals=favMeals;
        this.context=context;
        this.meals=meals;
        this.favHandler=favHandler;
    }

    public void updateMeals(List<Meal> meals) {
        this.meals.clear();
        this.meals.addAll(meals);
        notifyDataSetChanged();
    }


    public void updateFavMeals(List<FavMeal> favMeals)
    {
        this.favMeals.clear();
        this.favMeals.addAll(favMeals);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.meal_home_item, parent, false);
        return new SearchAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Meal meal=meals.get(position);
        holder.textView.setText(meal.getName());


        holder.button.setSelected(isMealFavorite(meal.getId()));


//        FavMeal favMeal = new FavMeal(meal.getId(), meal.getName(), meal.getThumbnailUrl(),"");
        FavMeal favMeal = new FavMeal();
        favMeal.setId(meal.getId());
        favMeal.setName(meal.getName());
        favMeal.setThumbnailUrl(meal.getThumbnailUrl());


        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (isMealFavorite(meal.getId())) {
                    favHandler.delet(favMeal);
                    Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
                    holder.button.setSelected(false);
                } else {
                    favHandler.insert(favMeal);
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

                SearchFragmentDirections.ActionSearchFragmentToDetailsFragment action =
                        SearchFragmentDirections.actionSearchFragmentToDetailsFragment(meal.getId());

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
        return meals.size();
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
