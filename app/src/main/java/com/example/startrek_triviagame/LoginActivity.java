package com.example.startrek_triviagame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

/**
 * This is the login logic for the Star Trek Trivia Game application.
 *
 * @author Nicole Al-Sabah
 * Date: December 02, 2023
 */
public class LoginActivity extends AppCompatActivity {

    private EditText editUserName;
    private EditText editPassword;
    private Button loginButton;
    private Button signUpButton;
    private StarTrekGameDao starTrekGameDao;
    private String userName;
    private String password;
    private User user;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wireUpDisplay();

        getDatabase();

        checkUser();

        checkUserAndLaunchLandingPage();
    }

    private void checkUser() {
        preferences = this.getSharedPreferences("com.example.startrek_triviagame", MODE_PRIVATE);

        int userId = preferences.getInt("userId", -1);

        if (userId < 0) {
            return;
        }

        user = starTrekGameDao.getUserId(userId);
    }

    private void getDatabase() {
        starTrekGameDao = StarTrekGameDatabase.getDatabase(this).starTrekGameDao();
    }

    private void wireUpDisplay() {
        // Get references to views
        editUserName = findViewById(R.id.userNameTextView);
        editPassword = findViewById(R.id.passwordTextView);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);

        // Set click listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesFromDisplay();
                if (checkForUserInDb()) {
                    if (validatePassword()) {
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                        if (user != null) {
                            preferences.edit().putInt("userId", user.getUserId()).apply();
                            checkUserAndLaunchLandingPage();
                        } else {
                            Toast.makeText(LoginActivity.this, "Unexpected error: User object is null", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = RegisterActivity.intentFactory(LoginActivity.this);
                startActivity(intent);
            }
        });
    }

    private boolean validatePassword() {
        return user.getPassword().equals(password);
    }

    private void getValuesFromDisplay() {
        userName = editUserName.getText().toString();
        password = editPassword.getText().toString();
    }

    private boolean checkForUserInDb() {
        user = starTrekGameDao.getUserName(userName);
        if (user == null) {
            Toast.makeText(this, "User " + userName + " does not exist", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void checkUserAndLaunchLandingPage() {
        if (user == null) {
            return;
        }

        // Determine user type and launch landing page
        if (user.getIsAdmin()) {
            launchAdminLandingPageActivity();
        } else {
            launchUserLandingPageActivity();
        }
    }

    private void launchAdminLandingPageActivity() {
        Intent intent = new Intent(this, AdminLandingPageActivity.class);
        intent.putExtra("username", user.getUserName());
        startActivity(intent);
        finish();
    }

    private void launchUserLandingPageActivity() {
        Intent intent = new Intent(this, UserLandingPageActivity.class);
        intent.putExtra("username", user.getUserName());
        startActivity(intent);
        finish();
    }
}
