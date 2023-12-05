package com.example.startrek_triviagame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserLandingPageActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing_page);

        preferences = this.getSharedPreferences("com.example.startrek_triviagame", MODE_PRIVATE);

        // Initialize UI elements
        Button playGameButton = findViewById(R.id.user_play_game_button);
        Button viewScoresButton = findViewById(R.id.user_view_scores_button);
        Button logoutButton = findViewById(R.id.user_logout_button);

        // Click listeners
        playGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().putInt("userId", -1).apply();
                Toast.makeText(UserLandingPageActivity.this, "Logout Successful!", Toast.LENGTH_SHORT).show();
                getIntent().putExtra("userId", -1);
                startLoginActivity();
            }
        });
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
