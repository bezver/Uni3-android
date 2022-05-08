package com.example.usermanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.usermanager.Core.Models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserCreateEditActivity extends AppCompatActivity {
    TextView errorContainer;
    EditText firstname, lastname, email, address;
    Button submitButton, deleteButton;

    Pattern emailRegex =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    Pattern nameRegex =
            Pattern.compile("^[a-z ,.'-]+$", Pattern.CASE_INSENSITIVE);

    User user = null;
    String mode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create_edit);

        Bundle args = getIntent().getExtras();
        if (args != null) {
            user = (User) args.getSerializable(User.class.getSimpleName());
            mode = args.getString("mode");
        }

        firstname      = findViewById(R.id.editFirstname);
        lastname       = findViewById(R.id.editLastname);
        email          = findViewById(R.id.editEmail);
        address        = findViewById(R.id.editAddress);
        submitButton   = findViewById(R.id.submitButton);
        deleteButton   = findViewById(R.id.deleteButton);
        errorContainer = findViewById(R.id.error_container);

        if (mode != null && mode.equals("Update")) {
            submitButton.setText("Update");
        }
        else {
            mode = "Add";
            submitButton.setText("Add");
            deleteButton.setVisibility(View.GONE);
        }

        if (user != null) {
            firstname.setText(user.getFirstname());
            lastname.setText(user.getLastname());
            email.setText(user.getEmail());
            address.setText(user.getAddress());
        }
    }

    public void OnSubmitButtonClick(View view) {
        if (!validateForm()) {
            return;
        }
        User newUser = getUser();
        if (user != null) {
            newUser.setId(user.getId());
        }
        Intent intent = new Intent();

        intent.putExtra(User.class.getSimpleName(), newUser);
        intent.putExtra("mode", mode);

        setResult(RESULT_OK, intent);
        finish();
    }

    public void OnDeleteButtonClick(View view) {
        Intent intent = new Intent();

        intent.putExtra(User.class.getSimpleName(), user);
        intent.putExtra("mode", "Delete");

        setResult(RESULT_OK, intent);
        finish();
    }

    private User getUser() {
        String firstnameValue = firstname.getText().toString();
        String lastnameValue  = lastname.getText().toString();
        String emailValue     = email.getText().toString();
        String addressValue   = address.getText().toString();

        return new User(firstnameValue, lastnameValue, emailValue, addressValue, R.drawable.face);
    }

    private boolean validateForm() {
        hideError();
        User user = getUser();
        if (!nameRegex.matcher(user.getFirstname()).find()) {
            showError("Problem with Firstname");
            return false;
        }
        if (!nameRegex.matcher(user.getLastname()).find()) {
            showError("Problem with Lastname");
            return false;
        }
        if (!emailRegex.matcher(user.getEmail()).find()) {
            showError("Problem with Email");
            return false;
        }
        if (user.getAddress().isEmpty()) {
            showError("Problem with Address");
            return false;
        }
        return true;
    }

    private void showError(String message) {
        errorContainer.setText(message);
    }

    private void hideError() {
        errorContainer.setText("");
    }

}