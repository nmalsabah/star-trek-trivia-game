package com.example.startrek_triviagame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

/**
 * This is a ListAdapter file for the Star Trek Trivia Game application.
 *
 * @author Nicole Al-Sabah
 * Date: December 02, 2023
 */
public class StarTrekGameListAdapter extends ListAdapter<StarTrekGameItem, StarTrekGameViewHolder> {
    public StarTrekGameListAdapter(@NonNull DiffUtil.ItemCallback<StarTrekGameItem> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public StarTrekGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // Determine which layout to inflate based on viewType
        int layoutResId;
        switch (StarTrekGameItem.ItemType.values()[viewType]) {
            case USER:
                layoutResId = R.layout.user_item_layout;
                break;
            case TRIVIA_QUESTIONS:
                layoutResId = R.layout.trivia_question_item_layout;
                break;
            case SCORE:
                layoutResId = R.layout.score_item_layout;
                break;
            default:
                throw new IllegalArgumentException("Unknown viewType: " + viewType);
        }

        View itemView = inflater.inflate(layoutResId, parent, false);
        return new StarTrekGameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StarTrekGameViewHolder holder, int position) {
        // Bind data to ViewHolder based on viewType
        StarTrekGameItem item = getItem(position);
        if (item != null) {
            switch (item.getItemType()) {
                case USER:
                    User user = item.getUser();
                    // Bind user data to the ViewHolder...
                    holder.bindUser(user);
                    break;
                case TRIVIA_QUESTIONS:
                    TriviaQuestions triviaQuestions = item.getTriviaQuestion();
                    // Bind trivia question data to the ViewHolder...
                    holder.bindTriviaQuestions(triviaQuestions);
                    break;
                case SCORE:
                    ScoreHistory score = item.getScore();
                    // Bind score data to the ViewHolder...
                    holder.bindScore(score);
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Return viewType based on the type of entity at the position
        StarTrekGameItem item = getItem(position);
        return (item != null) ? item.getItemType().ordinal() : 0;
    }

    public static class StarTrekGameDiff extends DiffUtil.ItemCallback<StarTrekGameItem> {

        @Override
        public boolean areItemsTheSame(@NonNull StarTrekGameItem oldItem, @NonNull StarTrekGameItem newItem) {
            // Implement logic to check if items are the same based on some unique identifier
            return oldItem.getItemId() == newItem.getItemId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull StarTrekGameItem oldItem, @NonNull StarTrekGameItem newItem) {
            // Implement logic to check if the contents of items are the same
            return oldItem.equals(newItem);
        }
    }
}
