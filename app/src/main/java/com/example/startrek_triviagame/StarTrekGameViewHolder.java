package com.example.startrek_triviagame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * This is a ViewHolder file for the Star Trek Trivia Game application.
 *
 * @author Nicole Al-Sabah
 * Date: December 02, 2023
 */
public class StarTrekGameViewHolder extends RecyclerView.ViewHolder {

    private final TextView userNameTextView;
    private final TextView triviaQuestionTextView;
    private final TextView scoreTextView;

    public StarTrekGameViewHolder(View itemView) {
        super(itemView);

        userNameTextView = itemView.findViewById(R.id.userNameTextView);
        triviaQuestionTextView = itemView.findViewById(R.id.triviaQuestionTextView);
        scoreTextView = itemView.findViewById(R.id.scoreTextView);
    }

    public void bind(StarTrekGameItem starTrekGameItem) {
        // Hide all TextViews initially
        userNameTextView.setVisibility(View.GONE);
        triviaQuestionTextView.setVisibility(View.GONE);
        scoreTextView.setVisibility(View.GONE);

        switch (starTrekGameItem.getItemType()) {
            case USER:
                bindUser(starTrekGameItem.getUser());
                break;
            case TRIVIA_QUESTIONS:
                bindTriviaQuestions(starTrekGameItem.getTriviaQuestion());
                break;
            case SCORE:
                bindScore(starTrekGameItem.getScore());
                break;
        }
    }

    public void bindUser(User user) {
        userNameTextView.setVisibility(View.VISIBLE);
        triviaQuestionTextView.setVisibility(View.GONE);
        scoreTextView.setVisibility(View.GONE);

        userNameTextView.setText(user.getUserName());
    }

    public void bindTriviaQuestions(TriviaQuestions triviaQuestions) {
        userNameTextView.setVisibility(View.GONE);
        triviaQuestionTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.GONE);

        triviaQuestionTextView.setText(triviaQuestions.getTriviaQuestion());
    }

    public void bindScore(ScoreHistory score) {
        userNameTextView.setVisibility(View.GONE);
        triviaQuestionTextView.setVisibility(View.GONE);
        scoreTextView.setVisibility(View.VISIBLE);

        scoreTextView.setText(score.getScore());
    }

    static StarTrekGameViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new StarTrekGameViewHolder(view);
    }
}
