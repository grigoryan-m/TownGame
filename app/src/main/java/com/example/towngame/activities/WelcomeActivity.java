package com.example.towngame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.towngame.GameManager;
import com.example.towngame.R;
import com.example.towngame.playerSelection.PlayerAdapter;

public class WelcomeActivity extends AppCompatActivity {

    public static GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_welcome);

        TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        Button continueButton = findViewById(R.id.continueButton);

        // Retrieve the message from the Intent
        String message = getIntent().getStringExtra("WELCOME_MESSAGE");
        if (message != null) {
            welcomeMessage.setText(message);
        }
    }

    public void startNightActivity(View view){
        Intent intent = new Intent(WelcomeActivity.this, NightProfile.class);
        startActivity(intent);
    }
}
