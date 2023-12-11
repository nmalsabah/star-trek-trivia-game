//package com.example.startrek_triviagame;
//
//import android.app.Application;
//
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MediatorLiveData;
//import androidx.lifecycle.Transformations;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * This file represents the View Model for the Star Trek Trivia Game.
// *
// * @author Nicole Al-Sabah
// * Date: December 01, 2023
// */
//public class StarTrekGameViewModel extends AndroidViewModel {
//
//    private final StarTrekGameRepository repository;
//
//    private final List<User> allUsers;
//    private final List<TriviaQuestions> allTriviaQuestions;
//    private final LiveData<List<ScoreHistory>> allScores;
//    private StarTrekGameDao starTrekGameDao;
//
//    public StarTrekGameViewModel(Application application) {
//        super(application);
//        repository = new StarTrekGameRepository(application);
//        allUsers = repository.getAllUsers();
//        allTriviaQuestions = repository.getAllTriviaQuestions();
//        allScores = repository.getAllScores();
//    }
//
//    public List<User> getAllUsers() {
//        return allUsers;
//    }
//
//    public User getUserId(int userId) {
//        return repository.getUserId(userId);
//    }
//
//    public User getIsAdmin(boolean isAdmin) {
//        return repository.getIsAdmin(isAdmin);
//    }
//
//    public List<TriviaQuestions> getAllTriviaQuestions() {
//        return allTriviaQuestions; }
//
//    public LiveData<List<ScoreHistory>> getAllScores() {
//        return allScores; }
//
//    public LiveData<List<StarTrekGameItem>> getAllStarTrekGameItems() {
//        LiveData<List<StarTrekGameItem>> mergedLiveData = Transformations.map(
//                // Combine LiveData from different entities into StarTrekGameItem instances
//                new MediatorLiveData<List<StarTrekGameItem>>(),
//                input -> {
//                    // Use the input from all LiveData sources to create List<StarTrekGameItem>
//                    List<StarTrekGameItem> items = new ArrayList<>();
//
//                    List<User> users = starTrekGameDao.getAllUsers();
//                    List<TriviaQuestions> triviaQuestions = allTriviaQuestions;
//                    List<ScoreHistory> scores = allScores.getValue();
//
//                    if (users != null) {
//                        for (User user : users) {
//                            items.add(StarTrekGameItem.createUserItem(user, items));
//                        }
//                    }
//
//                    if (triviaQuestions != null) {
//                        for (TriviaQuestions triviaQuestion : triviaQuestions) {
//                            items.add(StarTrekGameItem.createTriviaQuestionItem(triviaQuestion, items));
//                        }
//                    }
//
//                    if (scores != null) {
//                        for (ScoreHistory score : scores) {
//                            items.add(StarTrekGameItem.createScoreItem(score, items));
//                        }
//                    }
//
//                    return items;
//                }
//        );
//
//        return mergedLiveData;
//    }
//
////    public void insertStarTrekGameItem(StarTrekGameItem starTrekGameItem) {
////        repository.insertStarTrekGameItem(starTrekGameItem);
////    }
//
//    public void insertUser(User user) {
//        repository.insertUser(user);
//    }
//
//    public void insertTriviaQuestion(TriviaQuestions triviaQuestions) {
//        repository.insertTriviaQuestion(triviaQuestions); }
//
//    public void insertScores(ScoreHistory scoreHistory) {
//        repository.insertScore(scoreHistory); }
//
//    public void updateUser(User user) {
//        repository.updateUser(user);
//    }
//
//    public void updateTriviaQuestion(TriviaQuestions triviaQuestions) {
//        repository.updateTriviaQuestion(triviaQuestions); }
//
//    public void updateScores(ScoreHistory scoreHistory) {
//        repository.updateScore(scoreHistory); }
//
//    public void deleteUser(User user) {
//        repository.deleteUser(user);
//    }
//
//    public void deleteTriviaQuestion(TriviaQuestions triviaQuestions) {
//        repository.deleteTriviaQuestion(triviaQuestions); }
//
//    public void deleteScores(ScoreHistory scoreHistory) {
//        repository.deleteScore(scoreHistory); }
//}
