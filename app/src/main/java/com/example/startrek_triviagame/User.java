package com.example.startrek_triviagame;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This file represents the UserInformation table.
 *
 * @author Nicole Al-Sabah
 * Date: December 01, 2023
 */
@Entity(tableName = "UserInformation")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "UserId")
    private int userId;

    @NonNull
    @ColumnInfo(name = "UserName")
    private String userName;

    @NonNull
    @ColumnInfo(name = "Password")
    private String password;

    @ColumnInfo(name = "IsAdmin")
    private boolean isAdmin;

    public User() {
    }

    public User(@NonNull String userName,@NonNull String password, boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
