//package com.example.ratatouilleapp.View.Home.PlanView;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//
//import com.example.ratatouilleapp.R;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Locale;
//
//public class WeekPickerDialog extends Dialog {
//
//    private Calendar calendar;
//    private TextView tvWeekRange;
//    private Button[] dayButtons;
//    private SimpleDateFormat dayFormat = new SimpleDateFormat("E", Locale.getDefault());
//    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//    ImageButton prevBtn;
//    ImageButton nextBtn;
//
//    public interface OnDateSelectedListener {
//        void onDateSelected(String selectedDate);
//    }
//
//    public WeekPickerDialog(@NonNull Context context, OnDateSelectedListener listener) {
//        super(context);
//        setContentView(R.layout.week_picker_dialog);
//
//        calendar = Calendar.getInstance();
//        tvWeekRange = findViewById(R.id.tvWeekRange);
//
//        dayButtons = new Button[]{
//                findViewById(R.id.btnDay1),
//                findViewById(R.id.btnDay2),
//                findViewById(R.id.btnDay3),
//                findViewById(R.id.btnDay4),
//                findViewById(R.id.btnDay5),
//                findViewById(R.id.btnDay6),
//                findViewById(R.id.btnDay7)
//        };
//
//     prevBtn = findViewById(R.id.btnPrevWeekD);
//     prevBtn.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View v) {
//             updateWeek(-1);
//
////             findViewById(R.id.btnPrevWeekD).setOnClickListener(v -> updateWeek(-1));
//
//         }
//     });
//        findViewById(R.id.btnNextWeekD).setOnClickListener(v -> updateWeek(1));
//
//        updateWeek(0);
//
//        // Set up the day buttons
//        for (Button dayButton : dayButtons) {
//            dayButton.setOnClickListener(v -> {
//                // Handle day selection
//                for (Button btn : dayButtons) btn.setSelected(false);
//                v.setSelected(true);
//
//                String selectedDate = (String) v.getTag();
//                listener.onDateSelected(selectedDate);
//                dismiss();
//            });
//        }
//    }
//
//    private void updateWeek(int weekOffset) {
//        calendar.add(Calendar.WEEK_OF_YEAR, weekOffset);
//
//        Calendar tempCalendar = (Calendar) calendar.clone();
//        tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//
//        StringBuilder weekRangeText = new StringBuilder("Week of ");
//
//        for (int i = 0; i < 7; i++) {
//            final String date = dateFormat.format(tempCalendar.getTime());
//            dayButtons[i].setTag(date);  // Store the date as the tag for each button
//            dayButtons[i].setText(dayFormat.format(tempCalendar.getTime()));
//
//            tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
//
//            if (i == 0) {
//                weekRangeText.append(dayFormat.format(tempCalendar.getTime()));
//            } else if (i == 6) {
//                weekRangeText.append(" - ").append(dayFormat.format(tempCalendar.getTime()));
//            }
//        }
//
//        tvWeekRange.setText(weekRangeText.toString());
//    }
//}
package com.example.ratatouilleapp.View.Home.PlanView;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ratatouilleapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class WeekPickerDialog extends Dialog {

    private Calendar calendar;
    private TextView tvWeekRange;
    private Button[] dayButtons;
    private SimpleDateFormat dayFormat = new SimpleDateFormat("E", Locale.getDefault());
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public interface OnDateSelectedListener {
        void onDateSelected(String selectedDate);
    }

    public WeekPickerDialog(@NonNull Context context, OnDateSelectedListener listener) {
        super(context);
        setContentView(R.layout.week_picker_dialog);

        calendar = Calendar.getInstance();
        tvWeekRange = findViewById(R.id.tvWeekRange);

        // Initialize day buttons
        dayButtons = new Button[]{
                findViewById(R.id.btnDay1),
                findViewById(R.id.btnDay2),
                findViewById(R.id.btnDay3),
                findViewById(R.id.btnDay4),
                findViewById(R.id.btnDay5),
                findViewById(R.id.btnDay6),
                findViewById(R.id.btnDay7)
        };

        // Set up previous and next week buttons
        ImageButton btnPrevWeek = findViewById(R.id.btnPrevWeekD);
        ImageButton btnNextWeek = findViewById(R.id.btnNextWeekD);

        btnPrevWeek.setOnClickListener(v -> updateWeek(-1));
        btnNextWeek.setOnClickListener(v -> updateWeek(1));

        // Initialize the week view
        updateWeek(0);

        // Set up day button click listeners
        for (Button dayButton : dayButtons) {
            dayButton.setOnClickListener(v -> {
                // Deselect all buttons and select the clicked one
                for (Button btn : dayButtons) {
                    btn.setSelected(false);
                }
                v.setSelected(true);

                // Get the selected date from the button tag
                String selectedDate = (String) v.getTag();
                listener.onDateSelected(selectedDate);
                dismiss();
            });
        }
    }

    private void updateWeek(int weekOffset) {
        calendar.add(Calendar.WEEK_OF_YEAR, weekOffset);

        Calendar tempCalendar = (Calendar) calendar.clone();
        tempCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        StringBuilder weekRangeText = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            final String date = dateFormat.format(tempCalendar.getTime());
            dayButtons[i].setTag(date);  // Store the date as the tag for each button
            dayButtons[i].setText(dayFormat.format(tempCalendar.getTime()));

            // Construct the week range text
            if (i == 0) {
                weekRangeText.append(dateFormat.format(tempCalendar.getTime()));
            } else if (i == 6) {
                weekRangeText.append(" - ").append(dateFormat.format(tempCalendar.getTime()));
            }

            tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Update the week range text view
        tvWeekRange.setText(weekRangeText.toString());
    }
}
