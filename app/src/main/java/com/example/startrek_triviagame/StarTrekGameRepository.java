package com.example.startrek_triviagame;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * This file represents the Star Trek Trivia Game repository.
 *
 * @author Nicole Al-Sabah
 * Date: December 01, 2023
 */
public class StarTrekGameRepository {

    private final StarTrekGameDao mStarTrekGameDao;

    private final LiveData<List<User>> allUsers;
    private final LiveData<List<TriviaQuestions>> allTriviaQuestions;
    private final LiveData<List<ScoreHistory>> allScores;

    public StarTrekGameRepository(Application application) {
        StarTrekGameDatabase db = StarTrekGameDatabase.getDatabase(application);
        mStarTrekGameDao = db.starTrekGameDao();
        allUsers = mStarTrekGameDao.getAllUsers();
        allTriviaQuestions = mStarTrekGameDao.getAllTriviaQuestions();
        allScores = mStarTrekGameDao.getAllScores();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<User> getUserId(int userId) {
        return mStarTrekGameDao.getUserId(userId);
    }

    public LiveData<List<User>> getIsAdmin(boolean isAdmin) {
        return mStarTrekGameDao.getIsAdmin(isAdmin);
    }

    public LiveData<List<TriviaQuestions>> getAllTriviaQuestions() {
        return allTriviaQuestions; }

    public LiveData<List<ScoreHistory>> getAllScores() {
        return allScores; }

    public void insertUser(User user) {
        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> mStarTrekGameDao.insertUser(user));
    }

    public void insertTriviaQuestion(TriviaQuestions triviaQuestions) {
        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> {
            mStarTrekGameDao.insertTriviaQuestion(triviaQuestions);
        });
    }

    public void insertScore(ScoreHistory scoreHistory) {
        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> {
            mStarTrekGameDao.insertScore(scoreHistory);
        });
    }

    public void updateUser(User user) {
        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> mStarTrekGameDao.updateUser(user));
    }

    public void updateTriviaQuestion(TriviaQuestions triviaQuestions) {
        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> {
            mStarTrekGameDao.updateTriviaQuestion(triviaQuestions);
        });
    }

    public void updateScore(ScoreHistory scoreHistory) {
        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> {
            mStarTrekGameDao.updateScore(scoreHistory);
        });
    }

    public void deleteUser(User user) {
        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> mStarTrekGameDao.deleteUser(user));
    }

    public void deleteTriviaQuestion(TriviaQuestions triviaQuestions) {
        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> {
            mStarTrekGameDao.deleteTriviaQuestion(triviaQuestions);
        });
    }

    public void deleteScore(ScoreHistory scoreHistory) {
        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> {
            mStarTrekGameDao.deleteScore(scoreHistory);
        });
    }

//    public void insertStarTrekGameItem(StarTrekGameItem starTrekGameItem) {
//        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> mStarTrekGameDao.insertStarTrekGameItem(starTrekGameItem));
//    }
}
