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

    @Query("SELECT * FROM UserInformation WHERE UserId = :userId")
    User getUserId(int userId);

    @Query("SELECT * FROM UserInformation WHERE UserName = :userName")
    User getUserName(String userName);

    @Query("SELECT * FROM UserInformation WHERE Password = :password")
    User getPassword(String password);

    @Query("SELECT * FROM UserInformation WHERE IsAdmin = :isAdmin")
    User getIsAdmin(boolean isAdmin);

    @Query("SELECT * FROM UserInformation")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM UserInformation WHERE UserName = :userName AND Password = :password")
    User getUserNameAndPassword(String userName, String password);

    @Query("SELECT * FROM UserInformation WHERE UserName = :userName AND Password = :password AND IsAdmin = :isAdmin")
    User getUserNameAndPasswordAndAdminStatus(String userName, String password, boolean isAdmin);

    @Query("SELECT * FROM UserInformation WHERE UserName = :userName AND IsAdmin = :isAdmin")
    User getUserNameAndAdminStatus(String userName, boolean isAdmin);

    @Query("SELECT * FROM UserInformation WHERE Password = :password AND IsAdmin = :isAdmin")
    User getPasswordAndAdminStatus(String password, boolean isAdmin);

    // TriviaQuestions queries
    @Insert
    void insertTriviaQuestion(TriviaQuestions triviaQuestions);

    @Update
    void updateTriviaQuestion(TriviaQuestions triviaQuestions);

    @Delete
    void deleteTriviaQuestion(TriviaQuestions triviaQuestions);

    @Query("SELECT * FROM TriviaQuestions")
    List<TriviaQuestions> getAllTriviaQuestions();

    @Query("SELECT * FROM TriviaQuestions WHERE TriviaQuestionId = :triviaQuestionId")
    TriviaQuestions getTriviaQuestionId(int triviaQuestionId);

    @Query("SELECT * FROM TriviaQuestions WHERE TriviaQuestion = :triviaQuestion")
    TriviaQuestions getTriviaQuestion(String triviaQuestion);

    // ScoreHistory queries
    @Insert
    void insertScore(ScoreHistory scoreHistory);

    @Update
    void updateScore(ScoreHistory scoreHistory);

    @Delete
    void deleteScore(ScoreHistory scoreHistory);

    @Query("SELECT * FROM ScoreHistory")
    LiveData<List<ScoreHistory>> getAllScores();

    @Query("SELECT * FROM ScoreHistory WHERE ScoreId = :scoreId")
    ScoreHistory getScoreId(int scoreId);

    @Query("SELECT * FROM ScoreHistory WHERE IsCorrectAnswer = :isCorrectAnswer")
    ScoreHistory getIsCorrectAnswer(String isCorrectAnswer);

    @Query("SELECT * FROM ScoreHistory WHERE Score = :score")
    ScoreHistory getScore(String score);
}
