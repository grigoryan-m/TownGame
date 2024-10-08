package com.example.towngame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.towngame.GameManager;
import com.example.towngame.R;

import com.example.towngame.playerSelection.PlayerAdapter;

public class NightProfile extends AppCompatActivity {

    public static GameManager gameManager;

    static {
        gameManager = new GameManager(PlayerAdapter.staticPlayers);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_night_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainContainer), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryVariant));

        TextView playerName = findViewById(R.id.name);
        playerName.setText(gameManager.players.get(GameManager.currentPlayerID).getName());
    }

    @Override
    public void onResume(){
        super.onResume();
        TextView playerName = findViewById(R.id.name);
        playerName.setText(gameManager.players.get(GameManager.currentPlayerID).getName());
    }

    public void showRole(View view){
        Intent intent = new Intent(NightProfile.this, RoleActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(NightProfile.this, MainActivity.class);
        startActivity(intent);
    }
}