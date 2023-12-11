package com.example.startrek_triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TriviaGameActivity extends AppCompatActivity {

    private List<TextView> triviaQuestionsTextViews;
    private List<RadioGroup> triviaQuestionsRadioGroups;
    private Button submitAnswersButton;
    private int[] correctAnswers = {1, 2, 0, 3, 1};
    private int userScore = 0;
    private StarTrekGameDao starTrekGameDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);

        starTrekGameDao = StarTrekGameDatabase.getDatabase(getApplicationContext()).starTrekGameDao();

        triviaQuestionsTextViews = new ArrayList<>();
        triviaQuestionsTextViews.add(findViewById(R.id.questionOneTextView));
        triviaQuestionsTextViews.add(findViewById(R.id.questionTwoTextView));
        triviaQuestionsTextViews.add(findViewById(R.id.questionThreeTextView));
        triviaQuestionsTextViews.add(findViewById(R.id.questionFourTextView));
        triviaQuestionsTextViews.add(findViewById(R.id.questionFiveTextView));

        triviaQuestionsRadioGroups = new ArrayList<>();
        triviaQuestionsRadioGroups.add(findViewById(R.id.questionOneRadioGroup));
        triviaQuestionsRadioGroups.add(findViewById(R.id.questionTwoRadioGroup));
        triviaQuestionsRadioGroups.add(findViewById(R.id.questionThreeRadioGroup));
        triviaQuestionsRadioGroups.add(findViewById(R.id.questionFourRadioGroup));
        triviaQuestionsRadioGroups.add(findViewById(R.id.questionFiveRadioGroup));

        submitAnswersButton = findViewById(R.id.submitAnswersButton);

        TriviaQuestionLoader triviaQuestionLoader = new TriviaQuestionLoader(starTrekGameDao);
        triviaQuestionLoader.loadTriviaQuestions();

        displayTriviaQuestions();
        submitAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < triviaQuestionsRadioGroups.size(); i++) {
                    int selectedId = triviaQuestionsRadioGroups.get(i).getCheckedRadioButtonId();
                    int correctId = triviaQuestionsRadioGroups.get(i).getChildAt(correctAnswers[i]).getId();
                    if (selectedId == correctId) {
                        userScore++;
                    }
                }
                String score = String.valueOf(userScore);
                int userId = getIntent().getIntExtra("userId", -1);
                ScoreHistory scoreHistory = new ScoreHistory("2020-12-03", "Yes", score, userId);
                scoreHistory.setUserId(userId);

                User user = starTrekGameDao.getUserId(userId);
                if (user != null) {
                    starTrekGameDao.insertScore(scoreHistory);
                } else {
                    Log.d("TriviaGameActivity", "User with ID " + userId + " not found.");
                }
//                starTrekGameDao.insertScore(scoreHistory);

                navigateToResultActivity();
            }
        });
    }

    private void displayTriviaQuestions() {
        List<TriviaQuestions> triviaQuestions = starTrekGameDao.getAllTriviaQuestions();
        for (int i = 0; i < triviaQuestions.size(); i++) {
            triviaQuestionsTextViews.get(i).setText(triviaQuestions.get(i).getTriviaQuestion());

            for (int j = 0; j < triviaQuestionsRadioGroups.get(i).getChildCount(); j++) {
                RadioButton radioButton = (RadioButton) triviaQuestionsRadioGroups.get(i).getChildAt(j);
                radioButton.setText(String.valueOf(triviaQuestions.get(i).getTriviaAnswer(j)));
            }
        }
    }

    private void navigateToResultActivity() {
        Intent intent = new Intent(TriviaGameActivity.this, ResultActivity.class);
        intent.putExtra("userScore", userScore);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);
        finish();
    }
}
