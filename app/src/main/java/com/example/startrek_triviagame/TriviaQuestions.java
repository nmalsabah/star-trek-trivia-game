package com.example.startrek_triviagame;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This file represents the TriviaQuestions table.
 *
 * @author Nicole Al-Sabah
 * Date: December 03, 2023
 */
@Entity(tableName = "TriviaQuestions")
public class TriviaQuestions {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "TriviaQuestionId")
    private int triviaQuestionId;

    @NonNull
    @ColumnInfo(name = "TriviaQuestion")
    private String triviaQuestion;

    @NonNull
    @ColumnInfo(name = "DifficultyLevel")
    private String difficultyLevel;

    public TriviaQuestions(@NonNull String triviaQuestion,@NonNull String difficultyLevel) {
        this.triviaQuestion = triviaQuestion;
        this.difficultyLevel = difficultyLevel;
    }

    public int getTriviaQuestionId() {
        return triviaQuestionId;
    }

    public void setTriviaQuestionId(int triviaQuestionId) {
        this.triviaQuestionId = triviaQuestionId;
    }

    @NonNull
    public String getTriviaQuestion() {
        return triviaQuestion;
    }

    public void setTriviaQuestion(@NonNull String triviaQuestion) {
        this.triviaQuestion = triviaQuestion;
    }

    @NonNull
    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(@NonNull String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
