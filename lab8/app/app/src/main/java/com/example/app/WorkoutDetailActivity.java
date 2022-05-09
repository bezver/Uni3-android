package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WorkoutDetailActivity extends AppCompatActivity {
    public static final String EXTRA_WORKOUT_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        Bundle args = getIntent().getExtras();
        if (args != null) {
            int workoutId = args.getInt(EXTRA_WORKOUT_ID);
            WorkoutDetailFragment details = (WorkoutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment);
            details.setWorkout(workoutId);
        }
    }
}