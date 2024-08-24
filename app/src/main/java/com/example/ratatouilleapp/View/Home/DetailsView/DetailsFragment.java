package com.example.ratatouilleapp.View.Home.DetailsView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ratatouilleapp.Model.Api.Meal;
import com.example.ratatouilleapp.Model.DB.PlanMeal.Plan;
import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.Presenter.DetailsPresenter;
import com.example.ratatouilleapp.R;
import com.example.ratatouilleapp.View.Home.PlanView.WeekPickerDialog;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;


public class DetailsFragment extends Fragment implements Idetails {

    private RecyclerView ingrediantview;
    private ImageView mealImg;
    private TextView mealTitle;
    private Button calBtn;
    private  Button favBtm;
    private YouTubePlayerView mealVideo;
    private TextView instruction;

    Meal meal;

    DetailsPresenter presenter;
    IngrediantAdapter adapter;

    private String selectedDate;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ingrediantview=view.findViewById(R.id.ingredientRecyclerView);
        mealImg=view.findViewById(R.id.mealImage);
        mealTitle=view.findViewById(R.id.mealTitle);
        calBtn=view.findViewById(R.id.addToCalendarBtn);
        favBtm=view.findViewById(R.id.addToFavoritesBtn);
        instruction=view.findViewById(R.id.instructions);
        mealVideo=view.findViewById(R.id.mealVideo);


        presenter =new DetailsPresenter(Respiratory.getInstance(this.getContext(),new FireBaseAuthHandler()),this);

        adapter=new IngrediantAdapter(this.getContext(),new ArrayList<>());

        ingrediantview.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL, false));
        ingrediantview.setAdapter(adapter);





        DetailsFragmentArgs detailsFragmentArgs = DetailsFragmentArgs.fromBundle(getArguments());
        detailsFragmentArgs.getMealId();
        Log.d("AmrAmin", "onViewCreated: " + detailsFragmentArgs.getMealId());

        presenter.getMealById(detailsFragmentArgs.getMealId());



        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                WeekPickerDialog weekPickerDialog = new WeekPickerDialog(getContext(), selectedDate -> {
                    Log.d("SelectedDate", "onClick: " + selectedDate);
                    // Use the selected date in your plan or wherever needed
                    presenter.insert(new Plan(meal.getId(), meal.getName(), meal.getThumbnailUrl(), selectedDate, ""));
                    Toast.makeText(getContext(), "Meal Added To Plan", Toast.LENGTH_SHORT).show();
                });
                weekPickerDialog.show();

//                // Use Calendar to get the current date
//                final Calendar calendar = Calendar.getInstance();
//                int year = calendar.get(Calendar.YEAR);
//                int month = calendar.get(Calendar.MONTH);
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//                // Now pass these values to the DatePickerDialog
//                DatePickerDialog datePickerDialog = new DatePickerDialog(
//                        getContext(),
//                        (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
//                             selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDayOfMonth;
//                            // Use selectedDate as needed
//                            Log.d("insideAmr", "onClick: "+ selectedDate);
//
//                            presenter.insert(new Plan(meal.getId(), meal.getName(), meal.getThumbnailUrl(),selectedDate,""));
//                        },
//                        year, month, day  // Pass the initialized values here
//                );
//                datePickerDialog.show();
            }
        });


    }

    public  String extractVideoId(String url) {
        // Split the URL on '?' to get the query part
        String[] parts = url.split("\\?");
        if (parts.length > 1) {
            // Split the query part on '&' to get individual parameters
            String query = parts[1];
            String[] queryParams = query.split("&");
            for (String param : queryParams) {
                // Split each parameter on '=' to separate key and value
                String[] keyValue = param.split("=");
                if (keyValue.length == 2 && "v".equals(keyValue[0])) {
                    return keyValue[1]; // Return the value of the 'v' parameter
                }
            }
        }
        return null; // Return null if the video ID is not found
    }

    @Override
    public void showMeal(List<Meal> meals) {
        meal= meals.get(0);

       mealTitle.setText(meal.getName());
       instruction.setText(meal.getInstructions());
      // meal.getIngredientsMeal();
        adapter.updateIngrediants(meal.getIngredientsMeal());

        Glide.with(this.getContext())
                .load(meal.getThumbnailUrl())
                .apply(new RequestOptions().override(200, 200))
                .into(mealImg);

                if (meal.getYoutubeUrl()!= null && !meal.getYoutubeUrl().isEmpty()) {
            getLifecycle().addObserver(mealVideo);
            mealVideo.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                    //if your url is something like this -> https://www.youtube.com/watch?v=EzyXVfyx7CU
                    Log.d("MealVideo", "updateMeal:"+extractVideoId(meal.getYoutubeUrl()));
                    Log.d("MealVideo", "updateMeal:"+meal.getYoutubeUrl());

                    youTubePlayer.loadVideo(extractVideoId(meal.getYoutubeUrl()), 0);
                }
            });
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

    }
}