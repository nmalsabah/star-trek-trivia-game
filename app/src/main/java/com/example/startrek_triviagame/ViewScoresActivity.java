package com.example.startrek_triviagame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewScoresActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Button exitButton;
    private Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<>());
        recyclerView.setAdapter(recyclerViewAdapter);

        StarTrekGameDao starTrekGameDao = StarTrekGameDatabase.getDatabase(getApplicationContext()).starTrekGameDao();
        int userId = getIntent().getIntExtra("userId", -1);

        LiveData<List<ScoreHistory>> scoreLiveData = starTrekGameDao.getAllScores(userId);

        scoreLiveData.observe(this, new Observer<List<ScoreHistory>>() {
            @Override
            public void onChanged(List<ScoreHistory> scores) {
                recyclerViewAdapter.updateData(scores);
            }
        });

        exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
