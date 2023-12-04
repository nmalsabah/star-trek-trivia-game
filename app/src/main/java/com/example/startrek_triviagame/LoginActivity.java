package com.example.startrek_triviagame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
    //private Button registerButton;
    private StarTrekGameDao starTrekGameDao;
    private String userName;
    private String password;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wireUpDisplay();

        getDatabase();
    }


    private void getDatabase() {
        starTrekGameDao = StarTrekGameDatabase.getDatabase(this).starTrekGameDao();
    }

    private void wireUpDisplay() {
        // Get references to views
        editUserName = findViewById(R.id.userNameTextView);
        editPassword = findViewById(R.id.passwordTextView);
        loginButton = findViewById(R.id.loginButton);
        //registerButton = findViewById(R.id.registerButton);

        // Set click listeners
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValuesFromDisplay();
                if (checkForUserInDb()) {
                    if (validatePassword()) {
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

//                        Intent intent = MainActivity.intentFactory(getApplicationContext(), user.getUserId());
//                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // RegisterActivity is the signup page file
            public void onClick(View v) {
                Intent intent = RegisterActivity.intentFactory(getApplicationContext());
                startActivity(intent);
            }
        });*/
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

    public static Intent intentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}
