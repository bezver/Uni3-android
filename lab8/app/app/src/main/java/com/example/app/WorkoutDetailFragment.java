package com.example.app;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class WorkoutDetailFragment extends Fragment {
    private Integer workoutId = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment childFragment = new StopwatchFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.stopwatch_fragment, childFragment).commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null && workoutId != null) {
            TextView title = (TextView) view.findViewById(R.id.detail_title);
            Workout workout = Workout.workouts[workoutId];
            title.setText(workout.getName());
            TextView description = (TextView) view.findViewById(R.id.detail_description);
            description.setText(workout.getDescription());
        }
    }

    public void setWorkout(int id) {
        this.workoutId = id;
    }
}
