package com.example.ratatouilleapp.View.Home.PlanView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ratatouilleapp.Model.DB.PlanMeal.Plan;
import com.example.ratatouilleapp.Model.Firebase.FireBaseAuthHandler;
import com.example.ratatouilleapp.Model.Repo.Respiratory;
import com.example.ratatouilleapp.Presenter.PlanPresenter;
import com.example.ratatouilleapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PlanFragment extends Fragment implements Iplan {

    private TextView tvCurrentWeek;
    private ImageButton btnPrevWeek, btnNextWeek;
    private Calendar calendar;

    PlanPresenter presenter;

    // Days RecyclerViews
    private RecyclerView monday;
    private RecyclerView tuesday;
    private RecyclerView wednesday;
    private RecyclerView thursday;
    private RecyclerView friday;
    private RecyclerView saturday;
    private RecyclerView sunday;

    // Adapters
    PlanAdapter mondayAdapter;
    PlanAdapter tuesdayAdapter;
    PlanAdapter wednesdayAdapter;
    PlanAdapter thursdayAdapter;
    PlanAdapter fridayAdapter;
    PlanAdapter saturdayAdapter;
    PlanAdapter sundayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new PlanPresenter(Respiratory.getInstance(this.getContext(), new FireBaseAuthHandler()), this);

        // Days RecyclerViews
        monday = view.findViewById(R.id.mondayRecycle);
        tuesday = view.findViewById(R.id.TusedayRecycle);
        wednesday = view.findViewById(R.id.wednesdayRecycle);
        thursday = view.findViewById(R.id.ThursdayRecycle);
        friday = view.findViewById(R.id.Fridayrecycle);
        saturday = view.findViewById(R.id.saturdayRecycle);
        sunday = view.findViewById(R.id.sundayRecycle);
//        saturday = view.findViewById(R.id.SaturdayRecycle);
//        sunday = view.findViewById(R.id.SundayRecycle);

        // Adapters initialization
        mondayAdapter = new PlanAdapter(this.getContext(), new ArrayList<>());
        tuesdayAdapter = new PlanAdapter(this.getContext(), new ArrayList<>());
        wednesdayAdapter = new PlanAdapter(this.getContext(), new ArrayList<>());
        thursdayAdapter = new PlanAdapter(this.getContext(), new ArrayList<>());
        fridayAdapter = new PlanAdapter(this.getContext(), new ArrayList<>());
        saturdayAdapter = new PlanAdapter(this.getContext(), new ArrayList<>());
        sundayAdapter = new PlanAdapter(this.getContext(), new ArrayList<>());

        // Link adapters to RecyclerViews
        monday.setAdapter(mondayAdapter);
        tuesday.setAdapter(tuesdayAdapter);
        wednesday.setAdapter(wednesdayAdapter);
        thursday.setAdapter(thursdayAdapter);
        friday.setAdapter(fridayAdapter);
        saturday.setAdapter(saturdayAdapter);
        sunday.setAdapter(sundayAdapter);

        tvCurrentWeek = view.findViewById(R.id.tvCurrentWeek);
        btnPrevWeek = view.findViewById(R.id.btnPrevWeek);
        btnNextWeek = view.findViewById(R.id.btnNextWeek);

        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Start with the current week

        updateWeekDisplay();

        btnPrevWeek.setOnClickListener(v -> {
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
            updateWeekDisplay();
        });

        btnNextWeek.setOnClickListener(v -> {
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            updateWeekDisplay();
        });
        updateVisibility();
    }

    private void updateWeekDisplay() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd", Locale.getDefault());

        // Get the start and end of the week
        String startOfWeek = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 6); // Move to the end of the week
        String endOfWeek = dateFormat.format(calendar.getTime());

        // Display the week range
        tvCurrentWeek.setText(startOfWeek + " - " + endOfWeek);

        // Move back to the start of the week for future calculations
        calendar.add(Calendar.DAY_OF_MONTH, -6);

        // Trigger a method to load meals for the selected week
        presenter.getPlans();
    }

    @Override
    public void showPlans(List<Plan> plans) {
        clearAdapters();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Loop through each plan and assign it to the appropriate day
        for (Plan plan : plans) {
            try {
                Date planDate = dateFormat.parse(plan.getPlanDate());
                Calendar planCalendar = Calendar.getInstance();
                planCalendar.setTime(planDate);

                // Filter only the plans within the current week
                if (isSameWeek(planCalendar, calendar)) {
                    int dayOfWeek = planCalendar.get(Calendar.DAY_OF_WEEK);
                    switch (dayOfWeek) {
                        case Calendar.MONDAY:
                            mondayAdapter.getCurrentPlans().add(plan);
                            mondayAdapter.notifyDataSetChanged();
                            break;
                        case Calendar.TUESDAY:
                            tuesdayAdapter.getCurrentPlans().add(plan);
                            tuesdayAdapter.notifyDataSetChanged();
                            break;
                        case Calendar.WEDNESDAY:
                            wednesdayAdapter.getCurrentPlans().add(plan);
                            wednesdayAdapter.notifyDataSetChanged();
                            break;
                        case Calendar.THURSDAY:
                            thursdayAdapter.getCurrentPlans().add(plan);
                            thursdayAdapter.notifyDataSetChanged();
                            break;
                        case Calendar.FRIDAY:
                            fridayAdapter.getCurrentPlans().add(plan);
                            fridayAdapter.notifyDataSetChanged();
                            break;
                        case Calendar.SATURDAY:
                            saturdayAdapter.getCurrentPlans().add(plan);
                            saturdayAdapter.notifyDataSetChanged();
                            break;
                        case Calendar.SUNDAY:
                            sundayAdapter.getCurrentPlans().add(plan);
                            sundayAdapter.notifyDataSetChanged();
                            break;
                    }
                }
            } catch (ParseException e) {
                Log.e("PlanFragment", "Date parsing error: " + e.getMessage());
            }
        }

        updateVisibility();
    }

    private boolean isSameWeek(Calendar planCalendar, Calendar currentCalendar) {
        return planCalendar.get(Calendar.WEEK_OF_YEAR) == currentCalendar.get(Calendar.WEEK_OF_YEAR) &&
                planCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR);
    }

    private void clearAdapters() {
        mondayAdapter.updatePlans(new ArrayList<>());
        tuesdayAdapter.updatePlans(new ArrayList<>());
        wednesdayAdapter.updatePlans(new ArrayList<>());
        thursdayAdapter.updatePlans(new ArrayList<>());
        fridayAdapter.updatePlans(new ArrayList<>());
        saturdayAdapter.updatePlans(new ArrayList<>());
        sundayAdapter.updatePlans(new ArrayList<>());
    }


    private void updateVisibility() {
        if (getView() != null) {
            toggleVisibility(monday, mondayAdapter.getItemCount(), getView().findViewById(R.id.noMealsMonday));
            toggleVisibility(tuesday, tuesdayAdapter.getItemCount(), getView().findViewById(R.id.noMealsTuesday));
            toggleVisibility(wednesday, wednesdayAdapter.getItemCount(), getView().findViewById(R.id.noMealsWednesday));
            toggleVisibility(thursday, thursdayAdapter.getItemCount(), getView().findViewById(R.id.noMealsThursday));
            toggleVisibility(friday, fridayAdapter.getItemCount(), getView().findViewById(R.id.noMealsFriday));
            toggleVisibility(saturday, saturdayAdapter.getItemCount(), getView().findViewById(R.id.noMealsSaturday));
            toggleVisibility(sunday, sundayAdapter.getItemCount(), getView().findViewById(R.id.noMealsSunday));
        } else {
            Log.e("PlanFragment", "View is null in updateVisibility()");
        }
    }

    private void toggleVisibility(RecyclerView recyclerView , int itemCount, View emptyView) {
        if (itemCount == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
}
}
