package com.example.startrek_triviagame;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * This dao file is for the Star Trek Trivia Game application.
 *
 * @author Nicole Al-Sabah
 * Date: December 01, 2023
 */
@Dao
public interface StarTrekGameDao {

    // UserInformation queries
    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM UserInformation")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM UserInformation WHERE UserId = :userId")
    User getUserId(int userId);

    @Query("SELECT * FROM UserInformation WHERE UserName = :userName")
    User getUserName(String userName);

    @Query("SELECT * FROM UserInformation WHERE Password = :password")
    User getPassword(String password);

    @Query("SELECT * FROM UserInformation WHERE IsAdmin = :isAdmin")
    User getIsAdmin(boolean isAdmin);

    // TriviaQuestions queries
    @Insert
    void insertTriviaQuestion(TriviaQuestions triviaQuestions);

    @Update
    void updateTriviaQuestion(TriviaQuestions triviaQuestions);

    @Delete
    void deleteTriviaQuestion(TriviaQuestions triviaQuestions);

    @Query("SELECT * FROM TriviaQuestions")
    List<TriviaQuestions> getAllTriviaQuestions();

    // ScoreHistory queries
    @Insert
    void insertScore(ScoreHistory scoreHistory);

    @Update
    void updateScore(ScoreHistory scoreHistory);

    @Delete
    void deleteScore(ScoreHistory scoreHistory);

    @Query("SELECT * FROM ScoreHistory WHERE UserId = :userId")
    LiveData<List<ScoreHistory>> getAllScores(int userId);

    @Query("SELECT * FROM ScoreHistory WHERE Score = :score")
    LiveData<ScoreHistory> getScore(String score);
}
