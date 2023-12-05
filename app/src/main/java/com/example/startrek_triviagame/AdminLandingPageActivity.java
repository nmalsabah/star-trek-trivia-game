package com.example.startrek_triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLandingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing_page);

        // Initialize UI elements
        Button playGameButton = findViewById(R.id.admin_play_game_button);
        Button viewScoresButton = findViewById(R.id.admin_view_scores_button);
        Button adminTasksButton = findViewById(R.id.admin_tasks_button);
        Button logoutButton = findViewById(R.id.admin_logout_button);

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

        adminTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdminLandingPageActivity.this, "Logout Successful!", Toast.LENGTH_SHORT).show();
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
