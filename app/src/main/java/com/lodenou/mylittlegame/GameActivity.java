package com.lodenou.mylittlegame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private MusicService mServ;
    StartActivity mStartActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String characterName = intent.getStringExtra(ViewDialog.EXTRA_CHARACTER_NAME);

        // Capture the layout's TextView and set the string as its text
        TextView textViewGameActivity = findViewById(R.id.text_view_game);
        textViewGameActivity.setText(characterName);
    }
}
