package com.example.startrek_triviagame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText editUserName;
    private EditText editPassword;
    private Button registerButton;
    private Button cancelButton;
    private StarTrekGameDao starTrekGameDao;
    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        wireUpDisplay();

        getDatabase();
    }

    private void getDatabase() {
        starTrekGameDao = StarTrekGameDatabase.getDatabase(this).starTrekGameDao();
    }

    private void wireUpDisplay() {
        editUserName = findViewById(R.id.registerUserNameTextView);
        editPassword = findViewById(R.id.registerPasswordTextView);
        registerButton = findViewById(R.id.registerButton);
        cancelButton = findViewById(R.id.cancelButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesFromDisplay();
                if (checkInputs()) {
                    if (checkUserInDb()) {
                        Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        // Perform user registration and handle success/failure
                        User newUser = new User(0,userName, password, false);
                        starTrekGameDao.insertUser(newUser);
                        Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                        finish(); // Finish the RegisterActivity and go back to LoginActivity
                    }
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finish the RegisterActivity and go back to LoginActivity
            }
        });
    }

    private boolean checkInputs() {
        if (userName.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void getValuesFromDisplay() {
        userName = editUserName.getText().toString();
        password = editPassword.getText().toString();
    }

    private boolean checkUserInDb() {
        User existingUser = starTrekGameDao.getUserName(userName);
        return existingUser != null;
    }

    // Intent factory for starting RegisterActivity
    public static Intent intentFactory(Context context) {
        return new Intent(context, RegisterActivity.class);
    }
}
