package com.example.startrek_triviagame;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    public void updateUser(User user) {
        starTrekGameDao.updateUser(user);
    }

    public void deleteUser(User user) {
        Log.d("AdminTasksViewModel", "User to delete: " + user.getUserName());
        starTrekGameDao.deleteUser(user);
    }

    public User getUserName(String userName) {
        return starTrekGameDao.getUserName(userName);
    }
}
