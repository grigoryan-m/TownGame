package com.example.towngame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.towngame.GameManager;
import com.example.towngame.R;

import org.w3c.dom.Text;

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
        } else if ("DAY_ACTIVITY".equals(getIntent().getStringExtra("NEXT_ACTIVITY"))) {
            startDayActivity();
        }
    }

    public void startDayActivity(){
        Intent intent = new Intent(WelcomeActivity.this, DayActivity.class);
    }


    public void startNightActivity(){
        Intent intent = new Intent(WelcomeActivity.this, NightProfile.class);
        startActivity(intent);
    }
}
