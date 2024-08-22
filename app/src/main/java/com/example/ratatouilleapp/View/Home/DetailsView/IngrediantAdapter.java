package com.example.ratatouilleapp.View.Home.DetailsView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ratatouilleapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IngrediantAdapter extends RecyclerView.Adapter<IngrediantAdapter.ViewHolder> {

    private final Context context;
    private final List<String> ingrediants;

    public IngrediantAdapter(Context context,List<String> ingrediants)
    {
     this.ingrediants=ingrediants;
     this.context=context;
    }

    public void updateIngrediants(List<String> ingrediants) {
        this.ingrediants.clear();
        this.ingrediants.addAll(ingrediants);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.ingrediant_item, parent, false);
        return new IngrediantAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String ingrediant=ingrediants.get(position);

        // set Title

        Glide.with(holder.itemView.getContext())
                .load("https://www.themealdb.com/images/ingredients/" + ingrediant + "-Small.png")
                .into(holder.ingrediantImage);
    }

    @Override
    public int getItemCount() {
        return  ingrediants.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View layout;
        public  final CircleImageView ingrediantImage;
        public final ConstraintLayout constraintLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            constraintLayout = layout.findViewById(R.id.IngrediantLayout);
            ingrediantImage=layout.findViewById(R.id.circularImageView);

        }
    }
}
