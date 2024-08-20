package com.example.ratatouilleapp.View.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ratatouilleapp.Model.Api.Category;
import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.R;
import com.example.ratatouilleapp.View.Home.HomeView.HomeFragmentDirections;

import java.util.List;

public class MealAdapter extends  RecyclerView.Adapter<MealAdapter.ViewHolder> {

    private final Context context;
    private final List<Meal> meals;


    public MealAdapter(Context context,List<Meal> meals)
    {
        this.context=context;
        this.meals=meals;
    }

    public void updateMeals(List<Meal> meals) {
        this.meals.clear();
        this.meals.addAll(meals);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.meal_home_item, parent, false);
        return new MealAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Meal meal=meals.get(position);
        holder.textView.setText(meal.getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeFragmentDirections.ActionHomeFragmentToDetailsFragment action =
                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(meal.getId());

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
}
