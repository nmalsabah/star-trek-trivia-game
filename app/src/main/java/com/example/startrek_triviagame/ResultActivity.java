package com.example.startrek_triviagame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    SharedPreferences preferences;
    private User user;
    private StarTrekGameDao starTrekGameDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        starTrekGameDao = StarTrekGameDatabase.getDatabase(getApplicationContext()).starTrekGameDao();
        preferences = this.getSharedPreferences("com.example.startrek_triviagame", MODE_PRIVATE);

        int userScore = getIntent().getIntExtra("userScore", 0);
        TextView resultTextView = findViewById(R.id.resultTextView);
        if (userScore >= 3) {
            resultTextView.setText("You Win!");
        } else {
            resultTextView.setText("You Lose!");
        }
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Your score is: " + userScore);

        Button playAgainButton = findViewById(R.id.playAgainButton);
        Button exitButton = findViewById(R.id.exitButton);

        playAgainButton.setOnClickListener(v -> {
            int userId = preferences.getInt("userId", -1);
            boolean isAdmin = getIntent().getBooleanExtra("isAdmin", false);

            startTriviaGameActivity(userId, isAdmin);
        });

        exitButton.setOnClickListener(v -> {
            int userId = preferences.getInt("userId", -1);
            user = starTrekGameDao.getUserId(userId);
            boolean isAdmin = user.getIsAdmin();
            String username = user.getUserName();

            if (isAdmin) {
                launchAdminLandingPageActivity(userId, username);
            } else {
                launchUserLandingPageActivity(userId, username);
            }
        });
    }

    private void startTriviaGameActivity(int userId, boolean isAdmin) {
        Intent intent;
        if (isAdmin) {
            intent = new Intent(ResultActivity.this, TriviaGameActivity.class);
        } else {
            intent = new Intent(ResultActivity.this, TriviaGameActivity.class);
            intent.putExtra("userId", userId);
            intent.putExtra("username", getIntent().getStringExtra("username"));
        }

        startActivity(intent);
        finish();
    }

    private void launchAdminLandingPageActivity(int userId, String username) {
        Intent intent = new Intent(this, AdminLandingPageActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private void launchUserLandingPageActivity(int userId, String username) {
        Intent intent = new Intent(this, UserLandingPageActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }
}
