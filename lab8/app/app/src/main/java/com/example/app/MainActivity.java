package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(int id) {
        View worcoutDetailFragment = findViewById(R.id.detail_fragment);
        if (worcoutDetailFragment != null) {
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            details.setWorkout(id);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.detail_fragment, details);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        else {
            Intent intent = new Intent(this, WorkoutDetailActivity.class);
            intent.putExtra(WorkoutDetailActivity.EXTRA_WORKOUT_ID, id);
            startActivity(intent);
        }
    }
}