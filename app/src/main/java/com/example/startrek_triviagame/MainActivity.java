package com.example.startrek_triviagame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StarTrekGameDao starTrekGameDao;
    private SharedPreferences preferences;
    private int userId;
    private boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDatabase();
        checkForUser();
        startLoginActivity();

        Toast toast = Toast.makeText(this, "Welcome to the Star Trek Trivia Game!", Toast.LENGTH_LONG);
        toast.show();

        userId = getIntent().getIntExtra("userId", -1);
        isAdmin = getIntent().getBooleanExtra("isAdmin", false);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void getDatabase() {
        starTrekGameDao = StarTrekGameDatabase.getDatabase(this).starTrekGameDao();
    }

    private void getPrefs() {
        preferences = this.getSharedPreferences("com.example.startrek_triviagame", MODE_PRIVATE);
    }

    public void checkForUser() {
        userId = getIntent().getIntExtra("userId", -1);
        if (userId != -1) {
            return;
        }
        if (preferences == null) {
            getPrefs();
        }
        userId = preferences.getInt("userId", -1);
        if (userId != -1) {
            return;
        }

        starTrekGameDao.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users.size() <= 0) {
                    User testUser = new User(0, "testuser1", "testuser1", false);
                    User adminUser = new User(0,"admin2", "admin2", true);
                    starTrekGameDao.insertUser(testUser);
                    starTrekGameDao.insertUser(adminUser);
                }
            }
        });
    }
}
