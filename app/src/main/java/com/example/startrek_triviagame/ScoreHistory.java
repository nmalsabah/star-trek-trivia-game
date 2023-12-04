package com.example.startrek_triviagame;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This file represents the ScoreHistory table.
 *
 * @author Nicole Al-Sabah
 * Date: December 03, 2023
 */
@Entity(tableName = "ScoreHistory")
public class ScoreHistory {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ScoreId")
    private int scoreId;

    //Do I have to do a foreign key here?

    @NonNull
    @ColumnInfo(name = "EffectiveDate")
    private String effectiveDate;

    @NonNull
    @ColumnInfo(name = "IsCorrectAnswer")
    private String isCorrectAnswer;

    @NonNull
    @ColumnInfo(name = "Score")
    private String score;

    public ScoreHistory(@NonNull String effectiveDate,@NonNull String isCorrectAnswer, @NonNull String score) {
        this.effectiveDate = effectiveDate;
        this.isCorrectAnswer = isCorrectAnswer;
        this.score = score;
    }

    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    @NonNull
    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(@NonNull String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @NonNull
    public String getIsCorrectAnswer() {
        return isCorrectAnswer;
    }

    public void setIsCorrectAnswer(@NonNull String isCorrectAnswer) {
        this.isCorrectAnswer = isCorrectAnswer;
    }

    @NonNull
    public String getScore() {
        return score;
    }

    public void setScore(@NonNull String score) {
        this.score = score;
    }
}
