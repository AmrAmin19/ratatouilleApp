package com.example.ratatouilleapp.View.Home.PlanView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.ratatouilleapp.Model.DB.PlanMeal.Plan;
import com.example.ratatouilleapp.R;

import java.util.List;

public class PlanAdapter extends  RecyclerView.Adapter<PlanAdapter.ViewHolder>{

    private final Context context;
    private final List<Plan> plans;
    private PlanHandler planHandler;

   public PlanAdapter (Context context,List<Plan> plans,PlanHandler planHandler)
   {
       this.planHandler=planHandler;
       this.context=context;
       this.plans=plans;
   }

    public void updatePlans(List<Plan> plans) {
        this.plans.clear();
        this.plans.addAll(plans);
        notifyDataSetChanged();
    }

    public List<Plan> getCurrentPlans() {
        return plans;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.plan_item, parent, false);
        return new PlanAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       Plan plan =plans.get(position);
       holder.textView.setText(plan.getMealName());

       holder.button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               planHandler.delete(plan);
           }
       });


       holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               PlanFragmentDirections.ActionPlanFragmentToDetailsFragment action =
                       PlanFragmentDirections.actionPlanFragmentToDetailsFragment(plan.getMealId());

               Navigation.findNavController(v).navigate(action);
               
           }
       });

        Glide.with(context)
                .load(plan.getMealImage())
                .apply(new RequestOptions().override(200, 200))
                .into(holder.imgView);

    }

    @Override
    public int getItemCount() {
        return plans.size();
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
            constraintLayout = layout.findViewById(R.id.planLayout);
            cardView=layout.findViewById(R.id.planCardView);
            imgView = layout.findViewById(R.id.planImageView);
            textView = layout.findViewById(R.id.planTitleTextView);
            button=layout.findViewById(R.id.planButton);


        }
    }


}
