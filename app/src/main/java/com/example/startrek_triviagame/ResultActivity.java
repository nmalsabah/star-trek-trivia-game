package com.example.startrek_triviagame;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        int userScore = getIntent().getIntExtra("userScore", 0);
        TextView resultTextView = findViewById(R.id.resultTextView);
        if (userScore >= 3) {
            resultTextView.setText("You Win!");
        } else {
            resultTextView.setText("You Lose!");
        }
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Your score is: " + userScore);
    }
}
