package com.example.startrek_triviagame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ScoreViewHolder>{

    private List<ScoreHistory> scores;

    public RecyclerViewAdapter(List<ScoreHistory> scores) {
        this.scores = scores;
    }

//    public RecyclerViewAdapter() {
//        this.scores = new ArrayList<>();
//    }

    public void updateData(List<ScoreHistory> scores) {
        this.scores = scores;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        ScoreHistory score = scores.get(position);
        holder.bind(score);
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public static class ScoreViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewScoreDate;
        private TextView textViewScoreValue;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewScoreDate = itemView.findViewById(R.id.textViewScoreDate);
            textViewScoreValue = itemView.findViewById(R.id.textViewScoreValue);
        }

        public void bind(ScoreHistory score) {
            textViewScoreDate.setText("Date: " + score.getDate());
            textViewScoreValue.setText("Score: " + score.getScore());
        }
    }
}
