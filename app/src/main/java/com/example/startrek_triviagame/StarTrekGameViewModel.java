package com.example.startrek_triviagame;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * This file represents the View Model for the Star Trek Trivia Game.
 *
 * @author Nicole Al-Sabah
 * Date: December 01, 2023
 */
public class StarTrekGameViewModel extends AndroidViewModel {

    private final StarTrekGameRepository repository;

    private final LiveData<List<User>> allUsers;
    //private LiveData<List<questions entity>> allQuestions;
    //put last table entity here like allScores

    public StarTrekGameViewModel(Application application) {
        super(application);
        repository = new StarTrekGameRepository(application);
        allUsers = repository.getAllUsers();
        //allQuestions = repository.getAllQuestions();
        //put last table entity here like allScores
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<User> getUserId(int userId) {
        return repository.getUserId(userId);
    }

    public LiveData<List<User>> getIsAdmin(boolean isAdmin) {
        return repository.getIsAdmin(isAdmin);
    }

    //public LiveData<List<questions entity>> getAllQuestions() {
    //    return allQuestions; }

    //public LiveData<List<scores entity>> getAllScores() {
    //    return allScores; }

    public void insertUser(User user) {
        repository.insertUser(user);
    }

    //public void insertQuestions(questions entity) {
    //    repository.insertQuestions(entity); }

    //public void insertScores(scores entity) {
    //    repository.insertScores(entity); }

    public void updateUser(User user) {
        repository.updateUser(user);
    }

    //public void updateQuestions(questions entity) {
    //    repository.updateQuestions(entity); }

    //public void updateScores(scores entity) {
    //    repository.updateScores(entity); }

    public void deleteUser(User user) {
        repository.deleteUser(user);
    }

    //public void deleteQuestions(questions entity) {
    //    repository.deleteQuestions(entity); }

    //public void deleteScores(scores entity) {
    //    repository.deleteScores(entity); }
}
