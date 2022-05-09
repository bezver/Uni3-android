package com.example.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

public class WorkoutListFragment extends ListFragment {
    interface WorkoutListListener {
        void itemClicked(int id);
    }

    private WorkoutListListener listener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] names = new String[Workout.workouts.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Workout.workouts[i].getName();
        }

        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Activity activity){
        super.onAttach(activity);
        this.listener = (WorkoutListListener) activity;
    }

    @Override
    public void onListItemClick(@NonNull ListView listView, @NonNull View view, int position, long id){
        if (listener != null){
            listener.itemClicked((int)id);
        }
    }
}
