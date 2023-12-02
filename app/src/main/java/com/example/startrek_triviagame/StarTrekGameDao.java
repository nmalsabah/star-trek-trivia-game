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

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM UserInformation WHERE UserId = userId")
    LiveData<User> getUserId(int userId);

    @Query("SELECT * FROM UserInformation WHERE UserName = userName")
    LiveData<User> getUserName(String userName);

    @Query("SELECT * FROM UserInformation WHERE Password = password")
    LiveData<User> getPassword(String password);

    @Query("SELECT * FROM UserInformation WHERE IsAdmin = isAdmin")
    LiveData<List<User>> getIsAdmin(boolean isAdmin);

    @Query("SELECT * FROM UserInformation")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM UserInformation WHERE UserName = userName AND Password = password")
    LiveData<User> getUserNameAndPassword(String userName, String password);

    @Query("SELECT * FROM UserInformation WHERE UserName = userName AND Password = password AND IsAdmin = isAdmin")
    LiveData<User> getUserNameAndPasswordAndAdminStatus(String userName, String password, boolean isAdmin);

    @Query("SELECT * FROM UserInformation WHERE UserName = userName AND IsAdmin = isAdmin")
    LiveData<User> getUserNameAndAdminStatus(String userName, boolean isAdmin);

    @Query("SELECT * FROM UserInformation WHERE Password = password AND IsAdmin = isAdmin")
    LiveData<User> getPasswordAndAdminStatus(String password, boolean isAdmin);
}
