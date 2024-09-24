package com.example.towngame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.towngame.GameManager;
import com.example.towngame.R;

public class WelcomeActivity extends AppCompatActivity {

    public static GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_welcome);

        TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        TextView welcomeTitle = findViewById(R.id.titleMessage);

        // Retrieve the message from the Intent
        String message = getIntent().getStringExtra("WELCOME_MESSAGE");
        if (message != null) {
            welcomeMessage.setText(message);
        }
        message = getIntent().getStringExtra("TITLE_MESSAGE");
        if(message != null){
            welcomeTitle.setText(message);
        }
    }

    public void startActivity(View view){
        if("NIGHT_ACTIVITY".equals(getIntent().getStringExtra("NEXT_ACTIVITY"))){
            startNightActivity();
        } else if ("DISCUSSION_ACTIVITY".equals(getIntent().getStringExtra("NEXT_ACTIVITY"))) {
            startDiscussionActivity();
        } else if ("MENU".equals(getIntent().getStringExtra("NEXT_ACTIVITY"))){
            startMenuActivity();
        }
    }

    public void startDiscussionActivity(){
        Intent intent = new Intent(WelcomeActivity.this, DiscussionActivity.class);
        startActivity(intent);
    }


    public void startNightActivity(){
        Intent intent = new Intent(WelcomeActivity.this, NightProfile.class);
        startActivity(intent);
    }

    public void startMenuActivity(){
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
