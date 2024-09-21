package com.example.towngame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.towngame.GameManager;
import com.example.towngame.R;

import org.w3c.dom.Text;

public class RoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_role);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TextView roleName = findViewById(R.id.roleName);
        TextView roleDescription = findViewById(R.id.roleDescription);
        roleName.setText(GameManager.players.get(GameManager.currentPlayerID).role.getName());
        roleDescription.setText(GameManager.players.get(GameManager.currentPlayerID).role.getDesciption());
    }

    public void nextPlayer(View view){
        if(GameManager.currentPlayerID < GameManager.players.size()){
            GameManager.currentPlayerID++;
        }
        Intent intent = new Intent(RoleActivity.this, NightProfile.class);
        startActivity(intent);
    }
}