package com.example.usermanager;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.usermanager.Core.Models.User;
import com.example.usermanager.Core.Repository.UserRepository.IUserRepository;
import com.example.usermanager.Core.Repository.UserRepository.UserRepository;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, ActivityResultCallback<ActivityResult> {
    IUserRepository userRepository;
    UserAdapter adapter;
    int sortColumn;

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.userRepository = new UserRepository(this);
        this.sortColumn = R.id.column_firstname;

        ListView userList = findViewById(R.id.userList);
        adapter = new UserAdapter(this, R.layout.list_item, new ArrayList<>());
        userList.setAdapter(adapter);
        userList.setOnItemClickListener(this);
        updateUserList();
    }

    public void onAddButtonClick(View view) {
        Intent intent = new Intent(this, UserCreateEditActivity.class);
        intent.putExtra("mode", "Add");

        resultLauncher.launch(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        User user = adapter.getItem(position);
        Intent intent = new Intent(this, UserCreateEditActivity.class);
        intent.putExtra(User.class.getSimpleName(), user);
        intent.putExtra("mode", "Update");

        resultLauncher.launch(intent);
    }

    @Override
    public void onActivityResult(ActivityResult result) {
        if(result.getResultCode() == Activity.RESULT_OK){
            Intent intent = result.getData();
            User user = (User) intent.getSerializableExtra(User.class.getSimpleName());
            String mode = intent.getStringExtra("mode");
            if (mode.equals("Add")) {
                userRepository.Add(user);
            }
            else if (mode.equals("Update")) {
                userRepository.Update(user);
            }
            else {
                userRepository.Delete(user.getId());
            }
            updateUserList();
        }
    }

    public void onSortButtonClicked(View view) {
        sortColumn = view.getId();
        updateUserList();
    }

    private void sortUserList() {
        Comparator<User> comparator;
        switch(sortColumn) {
            case R.id.column_firstname:
                comparator = new Comparator<User>() {
                    @Override
                    public int compare(User lhs, User rhs) {
                        return lhs.getFirstname().compareTo(rhs.getFirstname());
                    }
                };
                break;
            case R.id.column_lastname:
                comparator = new Comparator<User>() {
                    @Override
                    public int compare(User lhs, User rhs) {
                        return lhs.getLastname().compareTo(rhs.getLastname());
                    }
                };
                break;
            default:
                comparator = new Comparator<User>() {
                    @Override
                    public int compare(User lhs, User rhs) {
                        return lhs.getEmail().compareTo(rhs.getEmail());
                    }
                };
                break;
        }
        adapter.sort(comparator);
    }

    private void updateUserList() {
        adapter.clear();
        adapter.addAll(userRepository.GetAll());
        sortUserList();
        adapter.notifyDataSetChanged();
    }


}