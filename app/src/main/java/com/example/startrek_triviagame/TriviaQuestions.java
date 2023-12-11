package com.example.startrek_triviagame;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Arrays;
import java.util.List;

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

    @NonNull
    @ColumnInfo(name = "AnswerOne")
    private String answerOne;

    @NonNull
    @ColumnInfo(name = "AnswerTwo")
    private String answerTwo;

    @NonNull
    @ColumnInfo(name = "AnswerThree")
    private String answerThree;

    @NonNull
    @ColumnInfo(name = "AnswerFour")
    private String answerFour;

    @Ignore
    private List<String> triviaAnswers;

    public TriviaQuestions(@NonNull String triviaQuestion,@NonNull String difficultyLevel,
                           @NonNull String answerOne, @NonNull String answerTwo,
                           @NonNull String answerThree, @NonNull String answerFour) {
        this.triviaQuestion = triviaQuestion;
        this.difficultyLevel = difficultyLevel;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.triviaAnswers = Arrays.asList(answerOne, answerTwo, answerThree, answerFour);
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

    @NonNull
    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(@NonNull String answerOne) {
        this.answerOne = answerOne;
    }

    @NonNull
    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(@NonNull String answerTwo) {
        this.answerTwo = answerTwo;
    }

    @NonNull
    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(@NonNull String answerThree) {
        this.answerThree = answerThree;
    }

    @NonNull
    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(@NonNull String answerFour) {
        this.answerFour = answerFour;
    }

    public List<String> getTriviaAnswers() {
        return triviaAnswers;
    }

    public void setTriviaAnswers(List<String> triviaAnswers) {
        this.triviaAnswers = triviaAnswers;
    }

    public String getTriviaAnswer(int j) {
        int triviaAnswer = 0;
        switch (j) {
            case 0:
                return answerOne;
            case 1:
                return answerTwo;
            case 2:
                return answerThree;
            case 3:
                return answerFour;
        }
        return "";
    }
}
