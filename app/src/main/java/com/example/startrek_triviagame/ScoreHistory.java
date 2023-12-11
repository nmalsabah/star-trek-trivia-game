package com.example.startrek_triviagame;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * This file represents the ScoreHistory table.
 *
 * @author Nicole Al-Sabah
 * Date: December 03, 2023
 */
@Entity(
        tableName = "ScoreHistory", foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "UserId", childColumns = "UserId", onDelete = ForeignKey.CASCADE))
public class ScoreHistory {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ScoreId")
    private int scoreId;

    @ColumnInfo(name = "UserId")
    private int userId;

    @NonNull
    @ColumnInfo(name = "EffectiveDate")
    private String effectiveDate;

    @NonNull
    @ColumnInfo(name = "IsCorrectAnswer")
    private String isCorrectAnswer;

    @NonNull
    @ColumnInfo(name = "Score")
    private String score;

    public ScoreHistory(@NonNull String effectiveDate,@NonNull String isCorrectAnswer, @NonNull String score, @NonNull int userId) {
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
