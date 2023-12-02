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
    //private LiveData<List<questions entity>> allQuestions;
    //put last table entity here like allScores

    public StarTrekGameRepository(Application application) {
        StarTrekGameDatabase db = StarTrekGameDatabase.getDatabase(application);
        mStarTrekGameDao = db.starTrekGameDao();
        allUsers = mStarTrekGameDao.getAllUsers();
        //allQuestions = mStarTrekGameDao.getAllQuestions();
        //put last table entity here like allScores
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

    //public LiveData<List<questions entity>> getAllQuestions() {
    //    return allQuestions; }

    //public LiveData<List<scores entity>> getAllScores() {
    //    return allScores; }

    public void insertUser(User user) {
        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> mStarTrekGameDao.insertUser(user));
    }

    //public void insertQuestions(questions entity) {
    //    StarTrekGameDatabase.databaseWriteExecutor.execute(() -> {
    //        mStarTrekGameDao.insertQuestions(entity);
    //    });
    //}

    //public void insertScores(scores entity) {
    //    StarTrekGameDatabase.databaseWriteExecutor.execute(() -> {
    //        mStarTrekGameDao.insertScores(entity);
    //    });
    //}

    public void updateUser(User user) {
        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> mStarTrekGameDao.updateUser(user));
    }

    //public void updateQuestions(questions entity) {
    //    StarTrekGameDatabase.databaseWriteExecutor.execute(() -> {
    //        mStarTrekGameDao.updateQuestions(entity);
    //    });
    //}

    //public void updateScores(scores entity) {
    //    StarTrekGameDatabase.databaseWriteExecutor.execute(() -> {
    //        mStarTrekGameDao.updateScores(entity);
    //    });
    //}

    public void deleteUser(User user) {
        StarTrekGameDatabase.databaseWriteExecutor.execute(() -> mStarTrekGameDao.deleteUser(user));
    }

    //public void deleteQuestions(questions entity) {
    //    StarTrekGameDatabase.databaseWriteExecutor.execute(() -> {
    //        mStarTrekGameDao.deleteQuestions(entity);
    //    });
    //}

    //public void deleteScores(scores entity) {
    //    StarTrekGameDatabase.databaseWriteExecutor.execute(() -> {
    //        mStarTrekGameDao.deleteScores(entity);
    //    });
    //}
}
