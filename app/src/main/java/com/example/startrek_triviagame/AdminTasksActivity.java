package com.example.startrek_triviagame;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

// NEED TO DO LEFT FOR ADMIN TASKS: UPDATE AND REMOVE USERS DO NOT WORK.
// NOT UPDATING IN THE LIST AND NOT UPDATING IN THE DATABASE.
// NEED TO PUT AN EXIT BUTTON ON THE ADMIN TASKS PAGE.
// NEED TO MAKE THE PAGE SCROLLING IN XML, RUNNING OUT OF ROOM.

public class AdminTasksActivity extends AppCompatActivity {
    private Spinner viewUsersSpinner;
    private Spinner removeUserSpinner;
    private EditText newUsernameEditText;
    private EditText newPasswordEditText;
    private Spinner updateUserSpinner;
    private EditText updateUsernameEditText;
    private EditText updatePasswordEditText;
    private Button removeUserButton;
    private Button addUserButton;
    private Button updateUserButton;

    StarTrekGameDao starTrekGameDao;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_admin_tasks);

         viewUsersSpinner = findViewById(R.id.viewUsersSpinner);
         removeUserSpinner = findViewById(R.id.removeUserSpinner);
         newUsernameEditText = findViewById(R.id.newUsernameEditText);
         newPasswordEditText = findViewById(R.id.newPasswordEditText);
         updateUserSpinner = findViewById(R.id.updateUserSpinner);
         updateUsernameEditText = findViewById(R.id.updateUsernameEditText);
         updatePasswordEditText = findViewById(R.id.updatePasswordEditText);
         removeUserButton = findViewById(R.id.removeUserButton);
         addUserButton = findViewById(R.id.addUserButton);
         updateUserButton = findViewById(R.id.updateUserButton);

         starTrekGameDao = StarTrekGameDatabase.getDatabase(getApplicationContext()).starTrekGameDao();

         List<User> users = starTrekGameDao.getAllUsers();
         updateSpinners(users);

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

         updateUserButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 updateUser(v);
             }
         });
    }

    private void updateSpinners(List<User> users) {
       List<String> userNames = new ArrayList<>();
       for (User user : users) {
           userNames.add(user.getUserName());
       }

       ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userNames);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       viewUsersSpinner.setAdapter(adapter);
       removeUserSpinner.setAdapter(adapter);
       updateUserSpinner.setAdapter(adapter);
    }

    public void removeUser(View view) {
        String selectedUser = removeUserSpinner.getSelectedItem().toString();

        User removeUser = new User(selectedUser, "", false);
        starTrekGameDao.deleteUser(removeUser);

        Toast.makeText(this, selectedUser + " Removed Successfully!", Toast.LENGTH_SHORT).show();
    }

    public void addUser(View view) {
       String newUsername = newUsernameEditText.getText().toString();
       String newPassword = newPasswordEditText.getText().toString();

       if (newUsername.isEmpty() || newPassword.isEmpty()) {
           Toast.makeText(this, "Please enter both username and password.", Toast.LENGTH_SHORT).show();
           return;
       }

       User newUser = new User(newUsername, newPassword, false);
       starTrekGameDao.insertUser(newUser);

       List<User> users = starTrekGameDao.getAllUsers();
       updateSpinners(users);

       Toast.makeText(this, newUsername + " Added Successfully!", Toast.LENGTH_SHORT).show();
   }

    public void updateUser(View view) {
       String updateUsername = updateUsernameEditText.getText().toString();
       String updatePassword = updatePasswordEditText.getText().toString();

       if (updateUsername.isEmpty() || updatePassword.isEmpty()) {
           Toast.makeText(this, "Please enter both username and password.", Toast.LENGTH_SHORT).show();
           return;
       }

       User updateUser = new User(updateUsername, updatePassword, false);
       updateUser.setUserName(updateUsername);
       updateUser.setPassword(updatePassword);
       starTrekGameDao.updateUser(updateUser);

       List<User> users = starTrekGameDao.getAllUsers();
       updateSpinners(users);

       Toast.makeText(this, updateUsername + " Updated Successfully!", Toast.LENGTH_SHORT).show();
   }
}
