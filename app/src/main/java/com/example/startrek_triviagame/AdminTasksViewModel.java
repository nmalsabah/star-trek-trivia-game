package com.example.startrek_triviagame;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AdminTasksViewModel extends AndroidViewModel {

    private StarTrekGameDao starTrekGameDao;
    private LiveData<List<User>> allUsers;

    public AdminTasksViewModel(Application application) {
        super(application);
        starTrekGameDao = StarTrekGameDatabase.getDatabase(application).starTrekGameDao();
        allUsers = starTrekGameDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insertUser(User user) {
        starTrekGameDao.insertUser(user);
    }

    public void deleteUser(User user) {
        starTrekGameDao.deleteUser(user);
    }
}
