package com.example.startrek_triviagame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class NewGameActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.startrek_triviagame.REPLY";

    private EditText mEditUserView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        mEditUserView = findViewById(R.id.edit_user);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (mEditUserView.getText().toString().isEmpty()) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String user = mEditUserView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, user);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
