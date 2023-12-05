package com.example.startrek_triviagame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

//    public static final int NEW_GAME_ACTIVITY_REQUEST_CODE = 1;
//    private StarTrekGameViewModel viewModel;
    //private StarTrekGameListAdapter adapter;
    private StarTrekGameDao starTrekGameDao;

    private SharedPreferences preferences;
    private int userId;

    private boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDatabase();
        checkForUser();
        startLoginActivity();

        Toast toast = Toast.makeText(this, "Welcome to the Star Trek Trivia Game!", Toast.LENGTH_LONG);
        toast.show();

        userId = getIntent().getIntExtra("userId", -1);
        isAdmin = getIntent().getBooleanExtra("isAdmin", false);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void getDatabase() {
        starTrekGameDao = StarTrekGameDatabase.getDatabase(this).starTrekGameDao();
    }

    private void getPrefs() {
        preferences = this.getSharedPreferences("com.example.startrek_triviagame", MODE_PRIVATE);
    }

    public void checkForUser() {
        userId = getIntent().getIntExtra("userId", -1);
        if (userId != -1) {
            return;
        }
        if (preferences == null) {
            getPrefs();
        }
        userId = preferences.getInt("userId", -1);
        if (userId != -1) {
            return;
        }
//        List<User> users = (List<User>) starTrekGameDao.getAllUsers();
        StarTrekGameViewModel mStarTrekViewModel = new StarTrekGameViewModel(this.getApplication());
        mStarTrekViewModel.getAllUsers().observe(this, users -> {
            if (users.size() <= 0) {
                User testUser = new User("testuser1", "testuser1", false);
                User adminUser = new User("admin2", "admin2", true);
                starTrekGameDao.insertUser(testUser);
                starTrekGameDao.insertUser(adminUser);
            }
        });


//        Intent intent = LoginActivity.intentFactory(this, userId, isAdmin);
//        startActivity(intent);
    }

}


//        checkForUser();
//
//
//        addUserToPreference(userId);
//        loginUser(userId);
    //    RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        adapter = new StarTrekGameListAdapter(new StarTrekGameListAdapter.StarTrekGameDiff());
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        viewModel = new ViewModelProvider(this).get(StarTrekGameViewModel.class);
//        viewModel.getAllStarTrekGameItems().observe(this, starTrekGameItems -> {
//                    adapter.submitList(starTrekGameItems);
//                });

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == NEW_GAME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            StarTrekGameItem starTrekGameItem = new StarTrekGameItem(data.getStringExtra(NewGameActivity.EXTRA_REPLY));
//            viewModel.insertStarTrekGameItem(starTrekGameItem);
//        } else {
//            Toast.makeText(
//                    getApplicationContext(),
//                    R.string.empty_not_saved,
//                    Toast.LENGTH_LONG).show();
//        }
//    }


//
//    private void loginUser(int userId) {
//        user = mStarTrekGameDao.getUserId(userId);
//        invalidateOptionsMenu();
//    }
//
//    private void addUserToPreference(int userId) {
//        if (preferences == null) {
//            getPrefs();
//        }
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putInt("userId", userId);
//    }
//
//    private void clearUserFromIntent() {
//        getIntent().putExtra("userId", -1);
//    }
//
//    private void clearUserFromPref() {
//        addUserToPreference(-1);
//    }
//

//

//
//}
