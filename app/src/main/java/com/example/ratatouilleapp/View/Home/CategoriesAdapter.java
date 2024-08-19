package com.example.ratatouilleapp.View.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ratatouilleapp.Model.Api.Category;
import com.example.ratatouilleapp.R;

import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{

    private final Context context;
    private final List<Category> categories;

    private int selectedPosition = -1;
    HomeFragment homeFragment;




    public CategoriesAdapter(List<Category> categories, Context context, HomeFragment homeFragment) {
        this.context = context;
        this.categories = categories;
        this.homeFragment=homeFragment;

    }

    public void updateProducts(List<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.category_item, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int pos=position;

        Category meal = categories.get(position);
        holder.textView.setText(meal.getName());



        if (selectedPosition == position) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.primaryColor));
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousPosition = selectedPosition;
                selectedPosition =pos;

                notifyItemChanged(previousPosition);
                notifyItemChanged(selectedPosition);

//                homeFragment.updateTextView(meal.getName());
                homeFragment.updateListByCategory(meal.getName());
            }
        });

        Glide.with(context)
                .load(meal.getThumbnailUrl())
                .apply(new RequestOptions().override(200, 200))
                .into(holder.imgView);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View layout;
        public  final CardView cardView;
        public final ConstraintLayout constraintLayout;
        public final ImageView imgView;
        public final TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            constraintLayout = layout.findViewById(R.id.categoryLayout);
            cardView=layout.findViewById(R.id.ctegoryCardView);
            imgView = layout.findViewById(R.id.imageView);
            textView = layout.findViewById(R.id.textView);
        }
    }

}
