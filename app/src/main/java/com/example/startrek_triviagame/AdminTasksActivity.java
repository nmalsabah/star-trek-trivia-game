package com.example.startrek_triviagame;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class AdminTasksActivity extends AppCompatActivity {
    private Spinner viewUsersSpinner;
    private Spinner removeUserSpinner;
    private EditText newUsernameEditText;
    private EditText newPasswordEditText;
    private Button removeUserButton;
    private Button addUserButton;
    private Button exitAdminTasksButton;
    private AdminTasksViewModel adminTasksViewModel;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_admin_tasks);

         adminTasksViewModel = new ViewModelProvider(this).get(AdminTasksViewModel.class);

         viewUsersSpinner = findViewById(R.id.viewUsersSpinner);
         removeUserSpinner = findViewById(R.id.removeUserSpinner);
         newUsernameEditText = findViewById(R.id.newUsernameEditText);
         newPasswordEditText = findViewById(R.id.newPasswordEditText);
         removeUserButton = findViewById(R.id.removeUserButton);
         addUserButton = findViewById(R.id.addUserButton);
         exitAdminTasksButton = findViewById(R.id.exitButton);

         adminTasksViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
             @Override
             public void onChanged(List<User> users) {
                 updateSpinners(users);
             }
         });

         removeUserButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 removeUser(v);
             }
         });

         addUserButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 addUser(v);
             }
         });

         exitAdminTasksButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 exitAdminTasks();
             }
         });
    }

    void updateSpinners(List<User> users) {
       List<String> userNames = new ArrayList<>();
       for (User user : users) {
           userNames.add(user.getUserName() + " ID: " + user.getUserId());
       }

       ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userNames);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       viewUsersSpinner.setAdapter(adapter);
       removeUserSpinner.setAdapter(adapter);
    }

    public void removeUser(View view) {
        String selectedUser = removeUserSpinner.getSelectedItem().toString();

        int userId = extractUserId(selectedUser);

        User removeUser = new User(userId, selectedUser, "", false);
        adminTasksViewModel.deleteUser(removeUser);

        Toast.makeText(this, selectedUser + " Removed Successfully!", Toast.LENGTH_SHORT).show();
    }

    public void addUser(View view) {
       String newUsername = newUsernameEditText.getText().toString();
       String newPassword = newPasswordEditText.getText().toString();

       if (newUsername.isEmpty() || newPassword.isEmpty()) {
           Toast.makeText(this, "Please enter both username and password.", Toast.LENGTH_SHORT).show();
           return;
       }



       User newUser = new User(0, newUsername, newPassword, false);
       adminTasksViewModel.insertUser(newUser);

       Toast.makeText(this, newUsername + " Added Successfully!", Toast.LENGTH_SHORT).show();
   }

    private int extractUserId(String selectedUser) {
         int userId = -1;
         int index = selectedUser.indexOf("ID: ");
         if (index != -1) {
              String userIdString = selectedUser.substring(index + 4);
              userId = Integer.parseInt(userIdString);
         }
         return userId;
    }

    public void exitAdminTasks() {
         finish();
    }
}
