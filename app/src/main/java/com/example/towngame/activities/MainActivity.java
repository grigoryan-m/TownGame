package com.example.towngame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.towngame.GameManager;
import com.example.towngame.R;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainContainer), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        GameManager.nightNumber = 1;
        GameManager.players = new ArrayList<>();

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryVariant));

        Button startButton = findViewById(R.id.startButton); // Assuming this is your button's ID
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, PlayerSelectionActivity.class);
                startActivity(intent);

                overridePendingTransition(R.anim.slide_up, R.anim.slide_out_up);
            }
        });
        // Сброс всех переменных
    }
    public void clearCache(View view){
        GameManager.players = new ArrayList<>();
        try{
            File file = new File(this.getFilesDir(), "players.dat");
            boolean deleted = file.delete();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}