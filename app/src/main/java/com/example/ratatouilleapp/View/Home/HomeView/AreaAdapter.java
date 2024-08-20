package com.example.ratatouilleapp.View.Home.HomeView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ratatouilleapp.Model.Api.Area;
import com.example.ratatouilleapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {

    private final Context context;
    private final List<Area> areas;
    HomeFragment homeFragment;
    private int selectedPosition = -1;

    public AreaAdapter(Context context,List<Area> areas,HomeFragment homeFragment)
    {
        this.areas=areas;
        this.context=context;
        this.homeFragment=homeFragment;
    }

    public void updateAreas(List<Area> areas) {
        this.areas.clear();
        this.areas.addAll(areas);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.area_item, parent, false);
        return new AreaAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Area area=areas.get(position);
        int pos=position;

        // Get the name from the Area object
        String imageName = area.getName().toLowerCase();// assuming the drawable names are formatted like this
        Log.d("countryFlag", imageName);

        // Convert the image name to a drawable resource ID
        int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        // Set the drawable resource to the ImageView
        if (imageResId != 0) {
            holder.countryFlag.setImageResource(imageResId);
        } else {
            // Handle the case where the drawable resource was not found
            holder.countryFlag.setImageResource(R.drawable.signup); // Set a default image if not found
        }

        if (selectedPosition == position) {
            holder.countryFlag.setBorderWidth(10); // Set the border width in pixels
        holder.countryFlag.setBorderColor(context.getResources().getColor(R.color.primaryColor));
        } else {
            holder.countryFlag.setBorderWidth(10); // Set the border width in pixels
        holder.countryFlag.setBorderColor(context.getResources().getColor(R.color.white));
        }

//        holder.countryFlag.setBorderWidth(10); // Set the border width in pixels
//        holder.countryFlag.setBorderColor(context.getResources().getColor(R.color.border_color));

        holder.countryFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int previousPosition = selectedPosition;
                selectedPosition =pos;

                notifyItemChanged(previousPosition);
                notifyItemChanged(selectedPosition);
                homeFragment.updateListByArea(area.getName());
            }
        });


    }

    @Override
    public int getItemCount() {
        return areas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View layout;
        public  final CircleImageView countryFlag;
        public final ConstraintLayout constraintLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            constraintLayout = layout.findViewById(R.id.AreaLayout);
            countryFlag=layout.findViewById(R.id.circularImageView);

        }
    }
}
